/* eslint-disable react/prop-types */
import { useState } from 'react';
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';

function AddAdvertisementPopup({ showModal, handleClose, handleAddAdvertisement}) {
    const [articleName, setArticleName] = useState('');
    const [category, setCategory] = useState('');
    const [price, setPrice] = useState('');
    const [image, setImage] = useState(null);

    const handleImageChange = (event) => {
        const file = event.target.files[0];
        if (file) {
            setImage(URL.createObjectURL(file)); 
        }
    };

    const handleSubmit = (event) => {
        event.preventDefault();

        if (!articleName || !category || !price || !image) {
            alert('Please fill in all fields.');
            return;
        }

        handleAddAdvertisement(articleName, category, price, image);
        handleClose();
    };

    return (
        <Modal show={showModal} onHide={handleClose} centered className='add-advertisement-modal'>
            <Modal.Header closeButton className='modal-title-header'>
                <Modal.Title className='modal-title fw-bold'>Add New Advertisement</Modal.Title>
            </Modal.Header>
            <Modal.Body className='modal-body'>
                <Form onSubmit={handleSubmit}>
                    <Form.Group controlId="formArticleName" className="mb-3">
                        <Form.Label className='modal-label'>Article Name</Form.Label>
                        <Form.Control
                            type="text"
                            placeholder="Enter article name"
                            value={articleName}
                            onChange={(e) => setArticleName(e.target.value)}
                            required={true}
                        />
                    </Form.Group>

                    <Form.Group controlId="formCategory" className="mb-3">
                        <Form.Label className='modal-label'>Category</Form.Label>
                        <Form.Select
                            value={category}
                            onChange={(e) => setCategory(e.target.value)}
                            required={true}
                        >
                            <option value="" disabled>Select Category</option>
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

                    <Form.Group controlId="formPrice" className="mb-3">
                        <Form.Label className='modal-label'>Price</Form.Label>
                        <Form.Control
                            type="number"
                            placeholder="Set price"
                            value={price}
                            onChange={(e) => setPrice(e.target.value)}
                            required={true}
                        />
                    </Form.Group>

                    <Form.Group controlId="formImage" className="mb-3">
                        <Form.Label className='modal-label'>Upload Image</Form.Label>
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
            <Modal.Footer className='d-flex justify-content-end'>
                <Button onClick={handleClose}>
                    Close
                </Button>
                <Button type="submit" onClick={handleSubmit}>
                    Submit
                </Button>
            </Modal.Footer>
        </Modal>
    );
}

export default AddAdvertisementPopup;
