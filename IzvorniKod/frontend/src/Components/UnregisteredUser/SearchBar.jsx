import 'bootstrap/dist/css/bootstrap.min.css';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { FaSearch } from 'react-icons/fa';
import { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import { Container } from 'react-bootstrap';

function SearchBar() {
    const [searchQuery, setSearchQuery] = useState('');
    const navigate = useNavigate(); 

    const handleInputChange = (e) => {
        setSearchQuery(e.target.value);
    };

   
    const handleSearchSubmit = (e) => {
        e.preventDefault();

        navigate(`/search_result_page?query=${encodeURIComponent(searchQuery)}&owned=false`);
    };

    return (
        <Container fluid className='image-container'>
            <div className='text-center search-title'>
                Refined style. Redefined for you.
            </div>
            <Form className="search-bar-form-container d-flex justify-content-center" onSubmit={handleSearchSubmit}>
                <InputGroup className="search-bar-container">
                    <InputGroup.Text className='icon-container'>
                        <FaSearch className="search-bar-icon" />
                    </InputGroup.Text>
                    <Form.Control
                        type="search"
                        placeholder="Search for a clothing item..."
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

export default SearchBar;
