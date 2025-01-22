/* eslint-disable no-unused-vars */
/* eslint-disable react/no-unescaped-entities */
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState, useEffect } from 'react';
import Container from 'react-bootstrap/Container';
import Button from 'react-bootstrap/Button';
import AddClosetPopup from './AddClosetPopup';
import ClosetPreview from './ClosetPreview';

class Closet {
  constructor(name, numberOfDrawers, numberOfShelves, numberOfHangers) {
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

  const [selectedCloset, setSelectedCloset] = useState(null);
  const [showClosetPreview, setShowClosetPreview] = useState(false);

  useEffect(() => {
    const fetchClosets = async () => {
          try {
              const response = await fetch('/api/getUserOrmari', {
                  method: 'GET',
                  credentials: 'include',
              });
  
              if (response.ok) {
                  const data = await response.json();
                  let closetArray = [];
                  for (let entry of Object.entries(data)) {
                    let numberOfDrawers = 0;
                    let numberOfShelves = 0;
                    let numberOfHangers = 0;
                    for (let lokacija of entry[1]) {
                      if (lokacija["vrstaLokacije"] === "drawer") {
                        numberOfDrawers += 1;
                      } else if (lokacija["vrstaLokacije"] === "shelf") {
                        numberOfShelves += 1;
                      } else if (lokacija["vrstaLokacije"] === "hanger") {
                        numberOfHangers += 1;
                      }
                    }
                    closetArray.push(new Closet(entry[0], numberOfDrawers, numberOfShelves, numberOfHangers));
                  }
                  setClosets(closetArray);
                  setNumOfClosets(closetArray.length);
              } else {
                  console.error('Failed to fetch closets');
              }
          } catch (error) {
              console.error('Error:', error);
          }
      };

      fetchClosets();
    }, 
  []);

  const handleOpenClosetPreview = (closet) => {
    setSelectedCloset(closet);
    setShowClosetPreview(true);
  };

  const handleCloseClosetPreview = () => {
    setSelectedCloset(null);
    setShowClosetPreview(false);
  };

  const placeholderText = (
    <p className="text-center">
      You don't have any closets yet. <br />
      All created closets will be displayed here.
    </p>
  );

  const addCloset = () => {
    setShowModal(true);
  };

  const postCloset = (closet) => {
    fetch("/api/addOrmar", {
      method: 'POST',
      headers: {
        'Accept': 'application/json',
        'Content-Type': 'application/json',
      },
      credentials: 'include',
      body: JSON.stringify({
        "name": closet.name,
        "numberOfDrawers": closet.numberOfDrawers,
        "numberOfShelves": closet.numberOfShelves,
        "numberOfHangers": closet.numberOfHangers
      })
    })
  };

  const handleAddCloset = (name, drawers, shelves, hangers) => {
    if (
      drawers < 0 || drawers > 3 ||
      shelves < 1 || shelves > 5 ||
      hangers < 1 || hangers > 2
    ) {
      alert(
        "Invalid input: Please ensure drawers are between 0 and 3, shelves are between 1 and 5, and hangers are between 1 and 2."
      );
      setShowModal(false);
      return;
    }

    const newCloset = new Closet(name, drawers, shelves, hangers);
    postCloset(newCloset);

    setNumOfClosets((prevNumOfClosets) => prevNumOfClosets + 1);
    setClosets((prevClosets) => [...prevClosets, newCloset]);
    console.log("Closet added successfully:", newCloset);
    setShowModal(false); 
  };

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
                <div className="closet-list-container">
                  <ul className="closet-list m-0 p-0">
                    {closets.map((closet, index) => (
                      <li
                        key={index}
                        className="closet-element m-0 p-0"
                        onClick={() => handleOpenClosetPreview(closet)}
                      >
                        <img
                          src="/assets/images/closet.png"
                          className="closet-image mb-3"
                          alt="Closet"
                        />
                        <span>{closet.name}</span>
                      </li>
                    ))}
                    <li>
                      <span className="empty-placeholder">+</span>
                    </li>
                  </ul>
                </div>
                <div className="text-center mt-5">
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
      <ClosetPreview
        show={showClosetPreview}
        handleClose={handleCloseClosetPreview}
        closet={selectedCloset}
      />
    </>
  );
}

export default Closets;
