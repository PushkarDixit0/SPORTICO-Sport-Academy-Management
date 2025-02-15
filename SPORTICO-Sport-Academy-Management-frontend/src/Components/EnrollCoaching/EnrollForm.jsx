import React from 'react';
import styles from './Enrollform.module.css';

const EnrollForm = ({ coaching, closeModal }) => {
  if (!coaching) {
    return <p>No session data provided.</p>;
  }

  return (
    <div className={styles.modalOverlay} onClick={closeModal}>
      <div className={styles.modalContainer} onClick={(e) => e.stopPropagation()}>
        <button className={styles.closeButton} onClick={closeModal}>&times;</button>
        <h2 className={styles.modalHeader}>Enroll in Coaching Session</h2>
        <p className={styles.modalText}><strong>Branch:</strong> {coaching.session_Branch}</p>
        <p className={styles.modalText}><strong>Venue:</strong> {coaching.venue}</p>
        <p className={styles.modalText}><strong>Date:</strong> {coaching.createdOn}</p>
        <p className={styles.modalText}><strong>Coach ID:</strong> {coaching.coachID}</p>
        <button className={styles.confirmButton}>Confirm Enrollment</button>
      </div>
    </div>
  );
};

export default EnrollForm;
