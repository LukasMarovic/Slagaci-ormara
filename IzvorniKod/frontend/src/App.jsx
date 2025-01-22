import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './Components/UnregisteredUser/HomePage';
import Login from './Components/UnregisteredUser/Login';
import Register from './Components/UnregisteredUser/Register';
import RegisteredUserPage from './Components/RegisteredUser/RegisteredUserPage';
import AdvertiserPage from './Components/AdvertiserUser/AdvertiserPage';
function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<Login></Login>} />
          <Route path="/register" element={<Register></Register>} />
          <Route path="/registered_user" element={<RegisteredUserPage/>} />
          <Route path="/advertiser_user" element={<AdvertiserPage/>}></Route>

        </Routes>
      </div>
    </Router>
  );
}

export default App
