import 'bootstrap/dist/css/bootstrap.min.css';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { FaSearch } from 'react-icons/fa';
import { useState } from 'react'; 

function SearchBarReg() {
    const [selectedTab, setSelectedTab] = useState('myClosets');

    const handleTabClick = (tab) => {
        setSelectedTab(tab);
    };

    var searchBarText;

    if(selectedTab === 'myClosets'){
        searchBarText = 'Search your closets...';
    } else {
        searchBarText = 'Search the page for an article...'
    }

    return (
        <div className='image-container-reg'>
            {/* "Find your collection." text */}
            <div className='text-center search-title-reg'>
                Find your collection.
            </div>

            {/* Clickable section for My Closets and Search Articles */}
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

            {/* Search bar */}
            <Form className="d-flex justify-content-center">
                <InputGroup className="search-bar-container-reg">
                    <InputGroup.Text className='icon-container'>
                        <FaSearch className="search-bar-icon" />
                    </InputGroup.Text>
                    <Form.Control
                        type="search"
                        placeholder={searchBarText}
                        className="search-bar-text p-3"
                        aria-label="Search"
                    />
                </InputGroup>
            </Form>
        </div>
    );
}

export default SearchBarReg;
