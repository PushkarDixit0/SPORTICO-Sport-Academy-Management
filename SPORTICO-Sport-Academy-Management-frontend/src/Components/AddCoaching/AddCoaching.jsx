import React, { useState, useEffect } from 'react';
import { toast } from 'react-toastify';
import 'react-toastify/dist/ReactToastify.css';
import UserService from '../../Service/UserService';
import sportService from '../../Service/SportService';
import coachingSessionService from '../../Service/CoachingSessionService';
import Navbar from '../Navbar/Navbar';
import styles from './AddCoaching.module.css';

export default function AddCoaching() {
  const [formData, setFormData] = useState({
    description: '',
    venue: '',
    session_Branch: '',
    sportID: '',
    coachID: ''
  });

  const [sports, setSports] = useState([]);
  const [coaches, setCoaches] = useState([]);
  const [errors, setErrors] = useState({});

  useEffect(() => {
    const fetchSports = async () => {
      try {
        const response = await sportService.getAllSports();
        setSports(response.data);
      } catch (error) {
        console.error("Error fetching sports data", error);
      }
    };

    const fetchCoaches = async () => {
      try {
        const response = await UserService.getAllCoaches();
        setCoaches(response.data);
      } catch (error) {
        console.error("Error fetching coaches data", error);
      }
    };

    fetchSports();
    fetchCoaches();
  }, []);

  const onChangeHandler = (e) => {
    const { name, value } = e.target;
    setFormData({ ...formData, [name]: value });
    setErrors({ ...errors, [name]: '' });
  };

  const validateForm = () => {
    const newErrors = {};
    if (!formData.description.trim()) newErrors.description = 'Description is required';
    if (!formData.venue.trim()) newErrors.venue = 'Venue is required';
    if (!formData.session_Branch) newErrors.session_Branch = 'Please select a session batch';
    if (!formData.sportID) newErrors.sportID = 'Please select a sport';
    if (!formData.coachID) newErrors.coachID = 'Please select a coach';

    setErrors(newErrors);
    return Object.keys(newErrors).length === 0;
  };

  const handleSubmit = async (e) => {
    e.preventDefault();
    if (!validateForm()) return;

    try {
      const response = await coachingSessionService.addSession(formData);
      if (response) {
        toast.success('Coaching session created successfully!');
        setFormData({ description: '', venue: '', session_Branch: '', sportID: '', coachID: '' });
      }
    } catch (error) {
      toast.error('Failed to create coaching session');
    }
  };

  return (
    <div>
      <Navbar />
      <div className={styles.container}>
        <h3 className={styles.title}>Create Coaching Session</h3>
        <form onSubmit={handleSubmit}>
          <div className={styles.formGroup}>
            <label htmlFor="description">Description</label>
            <input
              type="text"
              id="description"
              name="description"
              className={`${styles.formControl} ${errors.description ? styles.invalidFeedback : ''}`}
              value={formData.description}
              onChange={onChangeHandler}
            />
            {errors.description && <div className={styles.invalidFeedback}>{errors.description}</div>}
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="venue">Venue</label>
            <input
              type="text"
              id="venue"
              name="venue"
              className={`${styles.formControl} ${errors.venue ? styles.invalidFeedback : ''}`}
              value={formData.venue}
              onChange={onChangeHandler}
            />
            {errors.venue && <div className={styles.invalidFeedback}>{errors.venue}</div>}
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="session_Branch">Select Session Batch</label>
            <select
              id="session_Branch"
              name="session_Branch"
              className={styles.formControl}
              value={formData.session_Branch}
              onChange={onChangeHandler}
            >
              <option value="">Select Session Batch</option>
              <option value="MORNING">Morning</option>
              <option value="AFTERNOON">Afternoon</option>
              <option value="EVENING">Evening</option>
            </select>
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="sportID">Select Sport</label>
            <select
              id="sportID"
              name="sportID"
              className={styles.formControl}
              value={formData.sportID}
              onChange={onChangeHandler}
            >
              <option value="">Select Sport</option>
              {sports.map(sport => (
                <option key={sport.id} value={sport.id}>{sport.name}</option>
              ))}
            </select>
          </div>

          <div className={styles.formGroup}>
            <label htmlFor="coachID">Select Coach</label>
            <select
              id="coachID"
              name="coachID"
              className={styles.formControl}
              value={formData.coachID}
              onChange={onChangeHandler}
            >
              <option value="">Select Coach</option>
              {coaches.map(coach => (
                <option key={coach.id} value={coach.id}>{`Mr. ${coach.fname} ${coach.lname}`}</option>
              ))}
            </select>
          </div>

          <button type="submit" className={styles.btnPrimary}>Create Session</button>
        </form>
      </div>
    </div>
  );
}
