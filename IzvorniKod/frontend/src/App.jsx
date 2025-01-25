import './App.css';
import { BrowserRouter as Router, Route, Routes } from 'react-router-dom';
import HomePage from './Components/UnregisteredUser/HomePage';
import Login from './Components/UnregisteredUser/Login';
import Register from './Components/UnregisteredUser/Register';
import RegisteredUserPage from './Components/RegisteredUser/RegisteredUserPage';
import AdvertiserPage from './Components/AdvertiserUser/AdvertiserPage';
import SearchResultPage from './Components/SearchResultPage/SearchResultPage';
import RegisterAsAdvertiser from './Components/UnregisteredUser/RegisterAsAdvertiser'
import AdvertiserGalleryPage from './Components/AdvertiserGallery/AdvertiserGalleryPage';

function App() {
  return (
    <Router>
      <div>
        <Routes>
          <Route path="/" element={<HomePage />} />
          <Route path="/login" element={<Login></Login>} />
          <Route path="/register" element={<Register></Register>} />
          <Route path="/register_as_advertiser" element={<RegisterAsAdvertiser></RegisterAsAdvertiser>} />
          <Route path="/registered_user" element={<RegisteredUserPage/>} />
          <Route path="/advertiser_user" element={<AdvertiserPage/>}></Route>
          <Route path='/search_result_page' element={<SearchResultPage></SearchResultPage>}></Route>
          <Route path='/advertiser_gallery/:id' element={<AdvertiserGalleryPage></AdvertiserGalleryPage>}></Route>
        </Routes>
      </div>
    </Router>
  );
}

export default App