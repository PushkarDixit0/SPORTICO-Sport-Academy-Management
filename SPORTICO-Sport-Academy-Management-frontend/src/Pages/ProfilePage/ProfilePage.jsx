import React from 'react'
import Navbar from '../../Components/Navbar/Navbar'
import Profile from '../../Components/Profile/Profile'

function ProfilePage() {
  return (
    <div style={{ height: "100vh", display: "flex", flexDirection: "column", overflow: "hidden" }}>
    <Navbar />
    <Profile />
</div>

  )
}

export default ProfilePage