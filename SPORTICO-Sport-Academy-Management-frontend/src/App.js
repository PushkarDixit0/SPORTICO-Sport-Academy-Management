import './App.css';
import { ToastContainer } from 'react-toastify';
import LoginPage from './Pages/LoginPage/LoginPage';
import RegisterPage from './Pages/RegisterPage/RegisterPage';
import React, { Routes, Route, Navigate } from 'react-router-dom'
import ShowCoaching from './Components/ShowCoaching/ShowCoaching';
import AddCoaching from './Components/AddCoaching/AddCoaching';
import EnrollForm from './Components/EnrollCoaching/EnrollForm';

import ViewEnrolledPage from './Pages/ViewEnrolledPage/ViewEnrolledPage';
import ProfilePage from './Pages/ProfilePage/ProfilePage';
import HomePage from './Components/Home Page/HomePage';
import EnrollCoaching from './Pages/ViewCoachingEnroll/EnrollCoaching';
import TournamentsPage from './Pages/HomePage/TournamentsPage';
import AddCoach from './Components/AddCoach/AddCoach';



function App() {
  return (
    <>
      <ToastContainer />
      <Routes>
        {/* <Route path='/' element={<Navigate replace to='/home'></Navigate>}></Route> */}
        {/* <Route path='/home' element={<HomeComponent></HomeComponent>}></Route> */}

        <Route path='/' element={<HomePage />} />
        <Route path='/tournaments' element={<TournamentsPage />} />
        <Route path='/login' element={<LoginPage />} />
        <Route path='/register' element={<RegisterPage />} />
        <Route path='/enrollment' element={<ViewEnrolledPage />} />
        <Route path='/profile' element={<ProfilePage />} />
        <Route path='/coaching' element={<ShowCoaching></ShowCoaching>} />
        <Route path='/allcaochingenroll' element={<EnrollCoaching></EnrollCoaching>} />
        <Route path='/insertcoaching' element={<AddCoaching></AddCoaching>} />
        <Route path='/enrollCoachingSession' element={<EnrollForm></EnrollForm>} />
        <Route path="/add-coach" element={<AddCoach />} />

      </Routes>

    </>
  );
}

export default App;
