/* eslint-disable react/prop-types */
import { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

function AddAdvertisementPopupCloset({ showModal, handleClose, handleAddItemCloset }) {
    const [articleName, setArticleName] = useState('');
    const [category, setCategory] = useState('');
    const [price, setPrice] = useState('');
    const [image, setImage] = useState(null);
    const [season, setSeason] = useState('');
    const [color, setColor] = useState('');
    const [secondaryColor, setSecondaryColor] = useState('');
    const [formality, setFormality] = useState('');
    const [condition, setCondition] = useState('');
    const [description, setDescription] = useState(''); // New state for description

    const handleImageChange = (event) => {
        const file = event.target.files[0];
        if (file) {
            setImage(URL.createObjectURL(file));
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (
            !articleName ||
            !category ||
            !image ||
            !season ||
            !color ||
            !secondaryColor ||
            !formality ||
            !condition ||
            !description // Ensure description is filled
        ) {
            alert('Please fill in all fields.');
            return;
        }

        handleAddItemCloset(articleName, category, image, season, color, secondaryColor, formality, condition, description);
        handleClose();
    };


    return (
        <Modal show={showModal} onHide={handleClose} centered className="add-advertisement-modal">
            <Modal.Header closeButton className="modal-title-header">
                <Modal.Title className="modal-title fw-bold">Add New Advertisement</Modal.Title>
            </Modal.Header>
            <Modal.Body className="modal-body">
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="formArticleName" className="mb-3">
                        <Form.Label className="modal-label">Article Name</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Enter article name"
                            value={articleName}
                            onChange={(e) => setArticleName(e.target.value)}
                            required={true}
                        />
                    </Form.Group>

                    <Form.Group controlId="formCategory" className="mb-3">
                        <Form.Label className="modal-label">Category</Form.Label>
                        <Form.Select
                            value={category}
                            onChange={(e) => setCategory(e.target.value)}
                            required={true}
                        >
                            <option value="" disabled>
                                Select Category
                            </option>
                            <option value="T-Shirts">T-Shirts</option>
                            <option value="Shirts">Shirts</option>
                            <option value="Blouses">Blouses</option>
                            <option value="Pants">Pants</option>
                            <option value="Jeans">Jeans</option>
                            <option value="Jackets">Jackets</option>
                            <option value="Coats">Coats</option>
                            <option value="Sweatshirts & hoodies">Sweatshirts & hoodies</option>
                            <option value="Suits">Suits</option>
                            <option value="Sweaters">Sweaters</option>
                            <option value="Dresses">Dresses</option>
                            <option value="Skirts">Skirts</option>
                        </Form.Select>
                    </Form.Group>


                    <Form.Group controlId="formSeason" className="mb-3">
                        <Form.Label className="modal-label">Season</Form.Label>
                        <Form.Select
                            value={season}
                            onChange={(e) => setSeason(e.target.value)}
                            required={true}
                        >
                            <option value="" disabled>
                                Select Season
                            </option>
                            <option value="Spring">Spring</option>
                            <option value="Summer">Summer</option>
                            <option value="Fall">Fall</option>
                            <option value="Winter">Winter</option>
                        </Form.Select>
                    </Form.Group>

                    <Form.Group controlId="formColor" className="mb-3">
                        <Form.Label className="modal-label">Color</Form.Label>
                        <Form.Select
                            value={color}
                            onChange={(e) => setColor(e.target.value)}
                            required={true}
                        >
                            <option value="" disabled>
                                Select Color
                            </option>
                            <option value="Red">Red</option>
                            <option value="Blue">Blue</option>
                            <option value="Green">Green</option>
                            <option value="Yellow">Yellow</option>
                            <option value="Purple">Purple</option>
                            <option value="Black">Black</option>
                            <option value="White">White</option>
                        </Form.Select>
                    </Form.Group>

                    <Form.Group controlId="formSecondaryColor" className="mb-3">
                        <Form.Label className="modal-label">Secondary Color</Form.Label>
                        <Form.Select
                            value={secondaryColor}
                            onChange={(e) => setSecondaryColor(e.target.value)}
                            required={true}
                        >
                            <option value="" disabled>
                                Select Secondary Color
                            </option>
                            <option value="Red">Red</option>
                            <option value="Blue">Blue</option>
                            <option value="Green">Green</option>
                            <option value="Yellow">Yellow</option>
                            <option value="Purple">Purple</option>
                            <option value="Black">Black</option>
                            <option value="White">White</option>
                        </Form.Select>
                    </Form.Group>

                    <Form.Group controlId="formFormality" className="mb-3">
                        <Form.Label className="modal-label">Formality</Form.Label>
                        <Form.Select
                            value={formality}
                            onChange={(e) => setFormality(e.target.value)}
                            required={true}
                        >
                            <option value="" disabled>
                                Select Formality
                            </option>
                            <option value="Casual">Casual</option>
                            <option value="Business">Business</option>
                            <option value="Activewear">Activewear</option>
                            <option value="Homewear">Homewear</option>
                            <option value="Formal">Formal</option>
                        </Form.Select>
                    </Form.Group>

                    <Form.Group controlId="formCondition" className="mb-3">
                        <Form.Label className="modal-label">Condition</Form.Label>
                        <Form.Select
                            value={condition}
                            onChange={(e) => setCondition(e.target.value)}
                            required={true}
                        >
                            <option value="" disabled>
                                Select Condition
                            </option>
                            <option value="New">New</option>
                            <option value="Excellent">Excellent</option>
                            <option value="Very Good">Very Good</option>
                            <option value="Good">Good</option>
                            <option value="Bad">Bad</option>
                        </Form.Select>
                    </Form.Group>

                    <Form.Group controlId="formDescription" className="mb-3">
                        <Form.Label className="modal-label">Description</Form.Label>
                        <Form.Control
                            as="textarea"
                            rows={4}
                            placeholder="Enter a description of the item"
                            value={description}
                            onChange={(e) => setDescription(e.target.value)}
                            required
                        />
                    </Form.Group>

                    <Form.Group controlId="formImage" className="mb-3">
                        <Form.Label className="modal-label">Upload Image</Form.Label>
                        <Form.Control
                            type="file"
                            accept="image/*"
                            onChange={handleImageChange}
                            required={true}
                        />
                        {image && <img src={image} alt="Preview" className="mt-3" style={{ width: '100%', height: 'auto' }} />}
                    </Form.Group>
                </Form>
            </Modal.Body>
            <Modal.Footer className="d-flex justify-content-end">
                <Button onClick={handleClose}>Close</Button>
                <Button type="submit" onClick={handleSubmit}>
                    Submit
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

export default AddAdvertisementPopupCloset;
