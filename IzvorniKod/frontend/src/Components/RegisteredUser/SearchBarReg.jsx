import 'bootstrap/dist/css/bootstrap.min.css';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { FaSearch } from 'react-icons/fa';
import { useState } from 'react'; 
import { useNavigate } from 'react-router-dom';
import { Container } from 'react-bootstrap';

function SearchBarReg() {
    const [selectedTab, setSelectedTab] = useState('myClosets');

    const handleTabClick = (tab) => {
        setSelectedTab(tab);
    };

    var searchBarText;
    if(selectedTab === 'myClosets'){
        searchBarText = 'Search your closets...';
    } else {
        searchBarText = 'Search the page for an article...';
    }

    const [searchQuery, setSearchQuery] = useState('');
    const navigate = useNavigate();

    const handleInputChange = (e) => {
        setSearchQuery(e.target.value);
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();

        if (selectedTab === 'searchArticles') {
            navigate(`/search_result_page?query=${encodeURIComponent(searchQuery)}&owned=true`);
        } else {
            console.log("Searching within My Closets:", searchQuery);
        }
    };

    return (
        <Container fluid className='image-container-reg'>
            <div className='text-center search-title-reg'>
                Find your collection.
            </div>

            <div className='text-center mb-4'>
                <span
                    className={`search-tab ${selectedTab === 'myClosets' ? 'selected-tab' : ''}`}
                    onClick={() => handleTabClick('myClosets')}
                >
                    My Closets
                </span>
                <span className='search-tab-separate'> | </span>
                <span
                    className={`search-tab ${selectedTab === 'searchArticles' ? 'selected-tab' : ''}`}
                    onClick={() => handleTabClick('searchArticles')}
                >
                    Search Articles
                </span>
            </div>

            <Form className="d-flex justify-content-center" onSubmit={handleSearchSubmit}>
                <InputGroup className="search-bar-container-reg">
                    <InputGroup.Text className='icon-container'>
                        <FaSearch className="search-bar-icon" />
                    </InputGroup.Text>
                    <Form.Control
                        type="search"
                        placeholder={searchBarText}
                        className="search-bar-text p-3"
                        aria-label="Search"
                        value={searchQuery}
                        onChange={handleInputChange}
                    />
                </InputGroup>
            </Form>
        </Container>
    );
}

export default SearchBarReg;
