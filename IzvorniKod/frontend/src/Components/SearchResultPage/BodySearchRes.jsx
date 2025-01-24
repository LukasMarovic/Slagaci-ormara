/* eslint-disable react/prop-types */
/* eslint-disable no-unused-vars */
import 'bootstrap/dist/css/bootstrap.min.css';
import './search_result_css/style.css';
import Container from 'react-bootstrap/Container';
import SearchResultCard from './SearchResultCard';
import Form from 'react-bootstrap/Form';
import InputGroup from 'react-bootstrap/InputGroup';
import { FaSearch } from 'react-icons/fa';
import { useState, useEffect } from 'react';
import { useNavigate } from 'react-router-dom';

function BodySearchRes({ searchQuery, owned }) {
    const [searchQueryOnPage, setSearchQueryOnPage] = useState(searchQuery || '');
    const [results, setResults] = useState([]);
    const navigate = useNavigate();
    const [loading, setLoading] = useState(false);
    const [error, setError] = useState("");

    useEffect(() => {
        if (searchQuery) {
            setSearchQueryOnPage(searchQuery);
            fetchResults(searchQuery);
        }
    }, [searchQuery]);

    console.log(searchQuery);
    const fetchResults = async (query) => {
        setLoading(true);
        setError("");
        try {
            let link;
            if (owned) {
                link = `/api/searchUser?query=${query}`
            } else {
                link = `/api/search?query=${query}`
            }
            const response = await fetch(link);
            if (!response.ok) {
                throw new Error("Failed to fetch results");
            }
            const data = await response.json();
            setResults(data);
        } catch (err) {
            setError(err.message);
        } finally {
            setLoading(false);
        }
    };
    
    const handleInputChange = (e) => {
        setSearchQueryOnPage(e.target.value);
    };

    const handleSearchSubmit = (e) => {
        e.preventDefault();
        if (!owned) {
            navigate(`/search_result_page?query=${encodeURIComponent(searchQueryOnPage)}&owned=false`);
        } else {
            navigate(`/search_result_page?query=${encodeURIComponent(searchQueryOnPage)}&owned=true`);
        }
        
    };

    return (
        <>
            <Container className="search-result-container d-column align-items-center">
                <Form className="d-flex justify-content-center mt-5" onSubmit={handleSearchSubmit}>
                    <InputGroup className="search-bar-container-reg">
                        <InputGroup.Text className="icon-container">
                            <FaSearch className="search-bar-icon" />
                        </InputGroup.Text>
                        <Form.Control
                            type="search"
                            placeholder={searchQueryOnPage || 'Search...'} 
                            className="search-bar-text p-3"
                            aria-label="Search"
                            value={searchQueryOnPage} 
                            onChange={handleInputChange}
                        />
                    </InputGroup>
                </Form>

                <p className="search-result-title fw-bold pb-4 text-center">
                    Results for &apos;{searchQueryOnPage || ""}&apos;
                </p>
                {loading && <p>Loading...</p>}
                {error && <p>Error: {error}</p>}
                {results.length > 0 ? (
                <Container>
                    <SearchResultCard searchQuery={results} />
                </Container>) : (
                    !loading && <p>No results found.</p>
                )
                }
            </Container>
        </>
    );
}

export default BodySearchRes;
