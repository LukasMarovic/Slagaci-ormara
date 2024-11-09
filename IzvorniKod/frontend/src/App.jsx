import { useState } from 'react';
import './App.css';

import Header from './Components/Header';
import SearchBar from './Components/SearchBar';
import RegisterPromo from './Components/RegisterPromo';
import Spacer from './Components/Spacer';
import FeaturesContainer from './Components/FeaturesContainer';
import FeaturedArticles from './Components/FeaturedArticles';
import Footer from './Components/Footer';

function App() {
  return (
    <>
      <Header></Header>
      <SearchBar></SearchBar>
      <RegisterPromo></RegisterPromo>
      <FeaturesContainer></FeaturesContainer>
      <FeaturedArticles></FeaturedArticles>
      <Footer></Footer>
    </>
  )
}

export default App