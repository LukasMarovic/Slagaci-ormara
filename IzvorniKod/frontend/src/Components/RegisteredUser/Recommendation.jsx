import 'bootstrap/dist/css/bootstrap.min.css';
import Container from "react-bootstrap/Container";
import Form from "react-bootstrap/Form";
import { useState } from 'react';
import Button from 'react-bootstrap/Button';

function Recommendation() {
    const [weatherOption, setWeatherOption] = useState('');
    const [categories, setCategories] = useState([]);
    const [seasons, setSeasons] = useState([]);
    const [colors, setColors] = useState([]);
    const [formality, setFormality] = useState([]);

    const handleWeatherChange = (e) => {
        setWeatherOption(e.target.value);
    };

    const handleCheckboxChange = (e, setState, state) => {
        const value = e.target.value;
        if (e.target.checked) {
            setState([...state, value]);
        } else {
            setState(state.filter(item => item !== value));
        }
    };

    return (
        <Container className='recommendation-container d-flex justify-content-center align-items-center mt-5 mb-5'>
            <div className='recommendation-box d-flex'>
                <div className='recommendation-results'>
                    dasdas
                </div>
                <div className='recommendation-filter'>
                    <p className='filter-title text-center m-3'>Find the perfect combination</p>
                    <Form className='filter-form'>
                        <Form.Group controlId="category" className="mb-3">
                            <Form.Label className='filter-form-title'>Category</Form.Label>
                            <div>
                                <Form.Check
                                    type="checkbox"
                                    label="T-Shirts"
                                    value="t-shirts"
                                    checked={categories.includes('t-shirts')}
                                    onChange={(e) => handleCheckboxChange(e, setCategories, categories)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Shirts"
                                    value="shirts"
                                    checked={categories.includes('shirts')}
                                    onChange={(e) => handleCheckboxChange(e, setCategories, categories)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Pants"
                                    value="pants"
                                    checked={categories.includes('pants')}
                                    onChange={(e) => handleCheckboxChange(e, setCategories, categories)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Jackets"
                                    value="jackets"
                                    checked={categories.includes('jackets')}
                                    onChange={(e) => handleCheckboxChange(e, setCategories, categories)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Coats"
                                    value="coats"
                                    checked={categories.includes('coats')}
                                    onChange={(e) => handleCheckboxChange(e, setCategories, categories)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Sweatshirts"
                                    value="sweatshirts"
                                    checked={categories.includes('sweatshirts')}
                                    onChange={(e) => handleCheckboxChange(e, setCategories, categories)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Sweaters"
                                    value="sweaters"
                                    checked={categories.includes('sweaters')}
                                    onChange={(e) => handleCheckboxChange(e, setCategories, categories)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Dresses"
                                    value="dresses"
                                    checked={categories.includes('dresses')}
                                    onChange={(e) => handleCheckboxChange(e, setCategories, categories)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Skirts"
                                    value="skirts"
                                    checked={categories.includes('skirts')}
                                    onChange={(e) => handleCheckboxChange(e, setCategories, categories)}
                                    className='form-checkbox'
                                />
                            </div>
                        </Form.Group>

                        <Form.Group controlId="seasons" className="mb-3">
                            <Form.Label className='filter-form-title'>Seasons</Form.Label>
                            <div>
                                <Form.Check
                                    type="checkbox"
                                    label="Spring"
                                    value="spring"
                                    checked={seasons.includes('spring')}
                                    onChange={(e) => handleCheckboxChange(e, setSeasons, seasons)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Summer"
                                    value="summer"
                                    checked={seasons.includes('summer')}
                                    onChange={(e) => handleCheckboxChange(e, setSeasons, seasons)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Fall"
                                    value="fall"
                                    checked={seasons.includes('fall')}
                                    onChange={(e) => handleCheckboxChange(e, setSeasons, seasons)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Winter"
                                    value="winter"
                                    checked={seasons.includes('winter')}
                                    onChange={(e) => handleCheckboxChange(e, setSeasons, seasons)}
                                    className='form-checkbox'
                                />
                            </div>
                        </Form.Group>

                        <Form.Group controlId="colors" className="mb-3">
                            <Form.Label className='filter-form-title'>Colors</Form.Label>
                            <div>
                                <Form.Check
                                    type="checkbox"
                                    label="Red"
                                    value="red"
                                    checked={colors.includes('red')}
                                    onChange={(e) => handleCheckboxChange(e, setColors, colors)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Blue"
                                    value="blue"
                                    checked={colors.includes('blue')}
                                    onChange={(e) => handleCheckboxChange(e, setColors, colors)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Green"
                                    value="green"
                                    checked={colors.includes('green')}
                                    onChange={(e) => handleCheckboxChange(e, setColors, colors)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Yellow"
                                    value="yellow"
                                    checked={colors.includes('yellow')}
                                    onChange={(e) => handleCheckboxChange(e, setColors, colors)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Purple"
                                    value="purple"
                                    checked={colors.includes('purple')}
                                    onChange={(e) => handleCheckboxChange(e, setColors, colors)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Black"
                                    value="black"
                                    checked={colors.includes('black')}
                                    onChange={(e) => handleCheckboxChange(e, setColors, colors)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="White"
                                    value="white"
                                    checked={colors.includes('white')}
                                    onChange={(e) => handleCheckboxChange(e, setColors, colors)}
                                    className='form-checkbox'
                                />
                            </div>
                        </Form.Group>

                        <Form.Group controlId="formality" className="mb-3">
                            <Form.Label className='filter-form-title'>Formality</Form.Label>
                            <div>
                                <Form.Check
                                    type="checkbox"
                                    label="Casual"
                                    value="casual"
                                    checked={formality.includes('casual')}
                                    onChange={(e) => handleCheckboxChange(e, setFormality, formality)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Business"
                                    value="business"
                                    checked={formality.includes('business')}
                                    onChange={(e) => handleCheckboxChange(e, setFormality, formality)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Activewear"
                                    value="activewear"
                                    checked={formality.includes('activewear')}
                                    onChange={(e) => handleCheckboxChange(e, setFormality, formality)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Homewear"
                                    value="homewear"
                                    checked={formality.includes('homewear')}
                                    onChange={(e) => handleCheckboxChange(e, setFormality, formality)}
                                    className='form-checkbox'
                                />
                                <Form.Check
                                    type="checkbox"
                                    label="Formal"
                                    value="formal"
                                    checked={formality.includes('formal')}
                                    onChange={(e) => handleCheckboxChange(e, setFormality, formality)}
                                    className='form-checkbox'
                                />
                            </div>
                        </Form.Group>

                        <Form.Group controlId='weather' className='form-weather mb-5'>
                            <Form.Label> Take weather from your location into account? </Form.Label>
                            <div className='d-flex justify-content-evenly'>
                                <Form.Check
                                    type='radio'
                                    value='yes'
                                    label="Yes"
                                    checked={weatherOption === 'yes'}
                                    onChange={handleWeatherChange}
                                />
                                <Form.Check
                                    type='radio'
                                    value='no'
                                    label="No"
                                    checked={weatherOption === 'no'}
                                    onChange={handleWeatherChange}
                                />
                            </div>
                        </Form.Group>
                    </Form>
                    <div className='text-center mt-5'>
                        <Button className='search-button' type='submit'>
                            Search
                        </Button>
                    </div>
                </div>
            </div>
        </Container>
    );
}

export default Recommendation;
