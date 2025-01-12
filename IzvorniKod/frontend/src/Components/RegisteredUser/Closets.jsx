/* eslint-disable no-unused-vars */
/* eslint-disable react/no-unescaped-entities */
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import AddClosetPopup from './AddClosetPopup'; 

class Closet {
    constructor(name, numberOfDrawers, numberOfShelves, numberOfHangers){
        this.name = name;
        this.numberOfDrawers = numberOfDrawers;
        this.numberOfShelves = numberOfShelves;
        this.numberOfHangers = numberOfHangers;
    }

}

function Closets() {
  const [numOfClosets, setNumOfClosets] = useState(0); 
  const [showModal, setShowModal] = useState(false); 
  const [closets, setClosets] = useState([]);

  const placeholderText = (
    <p className="text-center">
      You don't have any closets yet. <br />
      All created closets will be displayed here.
    </p>
  );

  const addCloset = () => {
    setShowModal(true);
  };

  const handleAddCloset = (name, drawers, shelves, hangers) => {
    var newCloset = new Closet(name ,drawers, shelves, hangers)

    setNumOfClosets(prevNumOfClosets => prevNumOfClosets + 1); 
    setClosets(prevClosets => [...prevClosets, newCloset]);
    console.log("Closet added successfully") 
  }

  const handleCloseModal = () => {
    setShowModal(false);
  };

  return (
    <>
      <div className="closets-container pt-4 pb-3">
        <Container className="d-flex flex-column align-items-center">
          <p className="closets-title fw-bold">My Closets</p>
          <div>
            {numOfClosets === 0 ? (
              <div className="d-flex flex-column align-items-center">
                <p className="closets-placeholder-text mb-4">{placeholderText}</p>
                <Button className="add-closets-button p-3 my-4" onClick={addCloset}>
                  Create Virtual Closet
                </Button>
              </div>
            ) : (
              <>
              <div className='closet-list-container'>
                <ul className='closet-list m-0 p-0'>
                  {closets.map((closet, index) => {
                   return (
                   <li key={index} className='closet-element m-0 p-0'>
                      <img src='/public/assets/images/closet.png' className='closet-image mb-3'></img>
                      <span>{closet.name}</span>
                    </li>);
                  })}
                  <li>
                    <span className='empty-placeholder'>
                    +
                    </span>
                  </li>
                </ul>
                
              </div>
              <div className='text-center mt-5'>
              <Button className="add-closets-button p-3 my-4" onClick={addCloset}>
                  Add Virtual Closet
              </Button>
              </div>
              </>
            )}
          </div>
        </Container>
      </div>

      <AddClosetPopup
        show={showModal}
        handleClose={handleCloseModal} 
        handleAddCloset={handleAddCloset}
      />
    </>
  );
}

export default Closets;
