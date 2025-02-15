import React, { useState } from 'react';
import styles from "../Modals/FormModal.module.css";
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import TournamentService from '../../Service/TournamentService';

function TournamentFormModal({ modalval, edit, existingData, onClose, fetchData }) {
    const [tournamentData, setTournamentData] = useState({
        tournamentId: existingData?.tournamentId || 0,
        tournamentName: existingData?.tournamentName || "",
        tournamentDescription: existingData?.tournamentDescription || "",
        tournamentVenue: existingData?.tournamentVenue || "",
        sportName: existingData?.sportName || "",
        tournamentDate: existingData?.tournamentDate ? new Date(existingData.tournamentDate).toISOString().split('T')[0] : "",
        tournamentTime: existingData?.tournamentTime || "09:00",
        slots: existingData?.slots || 0
    });

    const [errors, setErrors] = useState({});
    const [formSubmitted, setFormSubmitted] = useState(false);

    const validate = () => {
        let newErrors = {};

        if (tournamentData.tournamentName.trim().length < 3) {
            newErrors.tournamentName = "Name must be at least 3 characters.";
        }
        if (tournamentData.tournamentVenue.trim().length < 3) {
            newErrors.tournamentVenue = "Venue must be at least 3 characters.";
        }
        if (!tournamentData.sportName.trim()) {
            newErrors.sportName = "Sport Name is required.";
        }
        if (!tournamentData.tournamentDate) {
            newErrors.tournamentDate = "Date is required.";
        } else if (new Date(tournamentData.tournamentDate) < new Date().setHours(0, 0, 0, 0)) {
            newErrors.tournamentDate = "Date must be today or later.";
        }
        if (!tournamentData.tournamentTime) {
            newErrors.tournamentTime = "Time is required.";
        }
        if (tournamentData.slots <= 0) {
            newErrors.slots = "Slots must be greater than 0.";
        }
        if (tournamentData.tournamentDescription.trim().length < 10) {
            newErrors.tournamentDescription = "Description must be at least 10 characters.";
        }

        setErrors(newErrors);
        return Object.keys(newErrors).length === 0;
    };

    const handleChange = (e) => {
        const { name, value } = e.target;
        setTournamentData({ ...tournamentData, [name]: value });

        // Only clear errors after submission attempt
        if (formSubmitted) {
            setErrors((prevErrors) => ({ ...prevErrors, [name]: "" }));
        }
    };

    const handleSubmit = async (e) => {
        e.preventDefault();
        setFormSubmitted(true); // Mark form as submitted
        if (!validate()) return; // Validate the form before submitting

        const finalData = { ...tournamentData };

        try {
            let response;
            if (edit) {
                response = await TournamentService.updateTournament(finalData);
            } else {
                response = await TournamentService.addTournament(finalData);
            }

            if (response) {
                toast.success(edit ? "Tournament Updated Successfully" : "Tournament Created Successfully");
                fetchData();
                onClose();
            }
        } catch (err) {
            console.error("Error:", err);
            toast.error("Something went wrong. Please try again.");
        }
    };

    return (
        <div className={styles.modal}>
            <div className={styles.overlay} onClick={onClose}></div>
            <div className={styles.modal_content}>
                <h2 className={styles.heading}>{edit ? "Edit Tournament" : "Add Tournament"}</h2>
                <form onSubmit={handleSubmit} className={styles.formContainer}>
                    <table className={styles.formTable}>
                        <tbody>
                            <tr>
                                <td><label htmlFor="tournamentName">Name</label></td>
                                <td>
                                    <input type="text" id="tournamentName" name="tournamentName" 
                                        value={tournamentData.tournamentName} onChange={handleChange} required />
                                    {formSubmitted && errors.tournamentName && <div className={styles.error}>{errors.tournamentName}</div>}
                                </td>
                            </tr>
                            <tr>
                                <td><label htmlFor="tournamentVenue">Venue</label></td>
                                <td>
                                    <input type="text" id="tournamentVenue" name="tournamentVenue" 
                                        value={tournamentData.tournamentVenue} onChange={handleChange} required />
                                    {formSubmitted && errors.tournamentVenue && <div className={styles.error}>{errors.tournamentVenue}</div>}
                                </td>
                            </tr>
                            <tr>
                                <td><label htmlFor="sportName">Sport Name</label></td>
                                <td>
                                    <input type="text" id="sportName" name="sportName" 
                                        value={tournamentData.sportName} onChange={handleChange} required />
                                    {formSubmitted && errors.sportName && <div className={styles.error}>{errors.sportName}</div>}
                                </td>
                            </tr>
                            <tr>
                                <td><label htmlFor="tournamentDate">Date</label></td>
                                <td>
                                    <input type="date" id="tournamentDate" name="tournamentDate" 
                                        value={tournamentData.tournamentDate} onChange={handleChange} required />
                                    {formSubmitted && errors.tournamentDate && <div className={styles.error}>{errors.tournamentDate}</div>}
                                </td>
                            </tr>
                            <tr>
                                <td><label htmlFor="tournamentTime">Time</label></td>
                                <td>
                                    <input type="time" id="tournamentTime" name="tournamentTime" 
                                        value={tournamentData.tournamentTime} onChange={handleChange} required />
                                    {formSubmitted && errors.tournamentTime && <div className={styles.error}>{errors.tournamentTime}</div>}
                                </td>
                            </tr>
                            <tr>
                                <td><label htmlFor="slots">Slots</label></td>
                                <td>
                                    <input type="number" id="slots" name="slots" 
                                        value={tournamentData.slots} onChange={handleChange} min="1" required />
                                    {formSubmitted && errors.slots && <div className={styles.error}>{errors.slots}</div>}
                                </td>
                            </tr>
                            <tr>
                                <td><label htmlFor="tournamentDescription">Description</label></td>
                                <td>
                                    <textarea id="tournamentDescription" name="tournamentDescription" 
                                        value={tournamentData.tournamentDescription} onChange={handleChange} required />
                                    {formSubmitted && errors.tournamentDescription && <div className={styles.error}>{errors.tournamentDescription}</div>}
                                </td>
                            </tr>
                        </tbody>
                    </table>
                    <div className={styles.btns}>
                        <button type="button" className={styles.cancel_btn} onClick={onClose}>Cancel</button>
                        <button type="submit" className={styles.confirm_btn}>Save</button>
                    </div>
                </form>
            </div>
        </div>
    );
}

export default TournamentFormModal;
