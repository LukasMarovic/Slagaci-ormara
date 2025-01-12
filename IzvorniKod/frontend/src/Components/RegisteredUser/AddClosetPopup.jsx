/* eslint-disable react/prop-types */
import Modal from 'react-bootstrap/Modal';
import Button from 'react-bootstrap/Button';
import Form from 'react-bootstrap/Form';
import { useState } from 'react';

function AddClosetPopup({ show, handleClose, handleAddCloset }) {

  const [drawers, setDrawers] = useState(0);
  const [shelves, setShelves] = useState(0);
  const [hangers, setHangers] = useState(0);
  const [name, setName] = useState('')

  const handleSubmit = (e) => {
    e.preventDefault();

    if(name === ''){
      alert("Please select a name for your closet!")
      return;
    } else if(drawers === 0 && shelves === 0){
      alert("The closet has to have at least 1 drawer or shelf!")
      return;
    }

    handleAddCloset(name, drawers, shelves, hangers);
    handleClose();
  };
  return (
    <Modal className='closet-popup'
      show={show} 
      onHide={handleClose} 
      centered
      fullscreen="sm-down"
      animation
      backdrop="static"
      keyboard={false}
    >
      <Modal.Header closeButton className='closet-header'>
        <Modal.Title className='closet-title'>Create a virtual closet</Modal.Title>
      </Modal.Header>
      <Modal.Body className='closet-body'>
      <Form onSubmit={handleSubmit}>
          <Form.Group className="d-flex align-items-center mb-2" controlId="formDrawers">
            <Form.Label className='closet-label'>Number of Drawers</Form.Label>
            <Form.Control className='closet-number'
              type="number"
              min={0}
              max={5}
              placeholder="Enter number of drawers"
              value={drawers}
              onChange={(e) => setDrawers(Number(e.target.value))}
            />
          </Form.Group>

          <Form.Group className="d-flex align-items-center justify-content-start mb-2" controlId="formShelves">
            <Form.Label className='closet-label'>Number of Shelves</Form.Label>
            <Form.Control className='closet-number'
              type="number"
              min={0}
              max={5}
              placeholder="Enter number of shelves"
              value={shelves}
              onChange={(e) => setShelves(Number(e.target.value))}
            />
          </Form.Group>

          <Form.Group className="d-flex align-items-center mb-2" controlId="formHangers">
            <Form.Label className='closet-label'>Number of Hangers</Form.Label>
            <Form.Control className='closet-number'
              type="number"
              min={0}
              max={2}
              placeholder="Enter number of hangers"
              value={hangers}
              onChange={(e) => setHangers(Number(e.target.value))}
            />
          </Form.Group>

          <Form.Group className="d-flex align-items-center" controlId="formHangers">
            <Form.Label className='closet-label'>Closet Name</Form.Label>
            <Form.Control className='closet-name'
              placeholder="Name"
              value={name}
              required
              onChange={(e) => setName(e.target.value)}
            />
          </Form.Group>
          
        </Form>
      </Modal.Body>
      <Modal.Footer className='closet-footer'>
        <Button onClick={handleClose} className='close-button'>
          Cancel
        </Button>
        <Button type='submit' onClick={handleSubmit} className='add-button'>
          Add Closet
        </Button>
      </Modal.Footer>
    </Modal>
  );
}

export default AddClosetPopup;
