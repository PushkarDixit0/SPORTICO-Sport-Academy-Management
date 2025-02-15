import React from "react";
import ReCAPTCHA from "react-google-recaptcha";

const Captcha = ({ onVerify }) => {
  return (
    <div className="mb-3 text-center">
      <ReCAPTCHA
        sitekey="6LcRotMqAAAAAGPEHwic-DbZXo4YV3H7HfzIYOam" 
        onChange={onVerify}
      />
    </div>
  );
};

export default Captcha;
