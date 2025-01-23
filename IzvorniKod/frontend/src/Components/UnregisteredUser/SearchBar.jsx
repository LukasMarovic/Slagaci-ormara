import 'bootstrap/dist/css/bootstrap.min.css';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import {FaSearch} from 'react-icons/fa';

function SearchBar() {
    return (
        <div className='image-container'>
            <div className='text-center search-title'>
                Refined style. Redefined for you.
            </div>
            <Form className="d-flex justify-content-center">
                <InputGroup className="search-bar-container">
                    <InputGroup.Text className='icon-container'>
                        <FaSearch className="search-bar-icon" />
                    </InputGroup.Text>
                    <Form.Control
                        type="search"
                        placeholder="Search for a clothing item..."
                        className="search-bar-text p-3"
                        aria-label="Search"
                    />
                </InputGroup>
            </Form>
        </div>
    );
}

export default SearchBar;
