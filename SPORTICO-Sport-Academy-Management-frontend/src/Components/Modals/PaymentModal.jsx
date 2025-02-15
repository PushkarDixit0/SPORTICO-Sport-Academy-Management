import React, { useState } from "react";
import styles from "./PaymentModal.module.css";
import TournamentEnrollmentService from "../../Service/TournamentEnrollmentService";
import { toast } from "react-toastify";

const PaymentModal = ({ tournamentId, userId, onClose, onSuccess }) => {
  const [cardNumber, setCardNumber] = useState("");
  const [cvv, setCvv] = useState("");
  const [expiry, setExpiry] = useState("");
  const [isProcessing, setIsProcessing] = useState(false);
  const [paymentStatus, setPaymentStatus] = useState(null);

  const validateCardDetails = (cardNumber, cvv, expiry) => {
    cardNumber = cardNumber.replace(/\s+/g, "");
  
    if (!/^\d{16}$/.test(cardNumber)) {
      toast.error("Invalid card number! Must be 16 digits.");
      return false;
    }
  
    if (!/^\d{3,4}$/.test(cvv)) {
      toast.error("Invalid CVV! Must be 3 or 4 digits.");
      return false;
    }
  
    const [month, year] = expiry.split("/").map(Number);
    const currentYear = new Date().getFullYear() % 100;
    const currentMonth = new Date().getMonth() + 1;
  
    if (!month || !year || month < 1 || month > 12 || (year < currentYear || (year === currentYear && month < currentMonth))) {
      toast.error("Expiry date must be in the future.");
      return false;
    }
  
    return true;
  };
  

  const handlePayment = async () => {
    if (!validateCardDetails(cardNumber, cvv, expiry)) {
      return;
    }
  
    setIsProcessing(true);
  
    try {
      const isPaymentSuccessful = Math.random() > 0.2;
  
      if (!isPaymentSuccessful) {
        toast.error("Payment failed! Please try again.");
        setPaymentStatus("failed");
        return;
      }
  
      toast.success("Payment successful!");
      setPaymentStatus("success");
  
      const response = await TournamentEnrollmentService.enrollInTournament(
        tournamentId,
        userId
      );
  
      if (response?.data) {
        console.log(response.data)
        toast.success("Enrollment successful!");
        onSuccess();
        onClose();
      } else {
        toast.error("Enrollment failed! Please contact support.");
      }
    } catch (error) {
      toast.error("An error occurred! Please try again.");
    } finally {
      setIsProcessing(false);
    }
  };
  

  return (
    <div className={styles.modal}>
      <div className={styles.modalContent}>
        <h2>Payment Gateway</h2>
        <input
          type="text"
          placeholder="Card Number"
          maxLength="19" 
          value={cardNumber}
          onChange={(e) =>
            setCardNumber(e.target.value.replace(/\s+/g, "").replace(/\D/g, ""))
          }
          className={styles.inputField}
        />
        <input
          type="text"
          placeholder="Expiry (MM/YY)"
          maxLength="5"
          value={expiry}
          onChange={(e) =>
            setExpiry(
              e.target.value
                .replace(/[^0-9/]/g, "")
                .replace(/^(\d{2})(\d{2})$/, "$1/$2")
            )
          }
          className={styles.inputField}
        />
        <input
          type="text"
          placeholder="CVV"
          maxLength="4"
          value={cvv}
          onChange={(e) => setCvv(e.target.value.replace(/\D/g, ""))}
          className={styles.inputField}
        />

        <button
          onClick={handlePayment}
          disabled={isProcessing}
          className={styles.button}
        >
          {isProcessing ? <span className={styles.loader}></span> : "Pay ₹500"}
        </button>

        {paymentStatus === "success" && (
          <p className={styles.success}>✅ Payment Successful!</p>
        )}
        {paymentStatus === "failed" && (
          <p className={styles.error}>❌ Payment Failed! Try Again.</p>
        )}

        <button onClick={onClose} className={styles.cancelButton}>
          Cancel
        </button>
      </div>
    </div>
  );
};

export default PaymentModal;
