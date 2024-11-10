import Header from './Header';
import SearchBar from './SearchBar';
import RegisterPromo from './RegisterPromo';
import Spacer from './Spacer';
import FeaturesContainer from './FeaturesContainer';
import FeaturedArticles from './FeaturedArticles';
import Footer from './Footer';
import Advertiser from './Advertiser';
import AdvertiseCards from './AdvertiseCards';

function HomePage(){
    return(
    <>
      <Header></Header>
      <SearchBar></SearchBar>
      <RegisterPromo></RegisterPromo>
      <FeaturesContainer></FeaturesContainer>
      <Advertiser></Advertiser>
      <FeaturedArticles></FeaturedArticles>
      <Footer></Footer>
    </>
    );
}

export default HomePage