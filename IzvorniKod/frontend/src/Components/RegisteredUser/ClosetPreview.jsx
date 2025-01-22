import { useState,useEffect  } from "react";
import { Container, Modal, Button, Card, Row, Col, Form } from "react-bootstrap";
import "../CustomCss/Closet.css";
import "../CustomCss/AddItemCloset.css";
import AddAdvertisementPopupCloset from "./AddAdvertisementPopupCloset";

const ClosetPreview = ({ show, handleClose, closet }) => {
  if (!closet) return null;

  const { name, numberOfDrawers, numberOfShelves, numberOfHangers } = closet;
  const [showDetails, setShowDetails] = useState(false);
  const [activeElement, setActiveElement] = useState(null);
  const [activeCardIndex, setActiveCardIndex] = useState(null);
  const [cardsData, setCardsData] = useState([]);
  const [showModal, setShowModal] = useState(false);
  const [checked, setChecked] = useState(false);

  const backendUrl = "https://your-backend-endpoint/api/cards"; // Replace with your actual backend URL

  // Handle API POST on cardsData change
  useEffect(() => {
    const postCardsData = async () => {
      try {
        const response = await fetch(backendUrl, {
          method: "POST",
          headers: {
            "Content-Type": "application/json",
          },
          body: JSON.stringify({ cards: cardsData }),
        });

        if (!response.ok) {
          throw new Error(`HTTP error! Status: ${response.status}`);
        }

        const result = await response.json();
        console.log("Successfully sent cardsData to backend:", result);
      } catch (error) {
        console.error("Error sending cardsData to backend:", error);
      }
    };

    if (cardsData.length > 0) {
      postCardsData();
    }
  }, [cardsData]);


  const handleCheckboxChange = (e) => {
    if (activeCardIndex !== null) {
      const updatedCards = [...cardsData];
      updatedCards[activeCardIndex].forSharing = e.target.checked;
      setCardsData(updatedCards);
      setChecked(e.target.checked);
      console.log("Updated cardsData:", updatedCards);
    }
  };

  const maxItems = 5;

  const handleAddItemCloset = (articleName, category, image, season, color, secondaryColor, formality, condition, description) => {
    const newItem = {
      elementName: activeElement,
      closetName: name,
      title: articleName,
      text: "Placeholder text",
      image: image,
      forSharing: false,
      category,
      season,
      color,
      secondaryColor,
      formality,
      condition,
      description,
    };

    setCardsData([...cardsData, newItem]);
    console.log("Updated cardsData:", [...cardsData, newItem]);
  };

  const handleAddItemClick = () => {
    setShowModal(true);
  };

  const handleCardClick = (index) => {
    if (activeCardIndex === index) {
      setActiveCardIndex(null);
      setChecked(false);
    } else {
      setActiveCardIndex(index);
      setChecked(cardsData[index]?.forSharing || false);
    }
  };

  const handlePageClick = (e) => {
    if (!e.target.closest(".card")) {
      setActiveCardIndex(null);
      setChecked(false);
    }
  };

  const renderCard = (index, elementName) => {
    const card = cardsData.filter((card) => card.elementName === elementName)[index];
    if (!card) return null;

    const isActive = activeCardIndex === cardsData.indexOf(card);

    return (
      <Card
        className={`custom-card ${isActive ? "active-card" : ""}`}
        key={`${elementName}-${index}`}
        onClick={() => handleCardClick(cardsData.indexOf(card))}
        style={{ border: isActive ? "2px solid blue" : "none" }}
      >
        <Card.Img
          variant="top"
          src={card.image}
          alt={`Card ${index + 1}`}
        />
        <Card.Body className="card-body-closet">
          <Card.Title className="custom-title">{card.title}</Card.Title>
          <Card.Text className="custom-text">{card.text}</Card.Text>
        </Card.Body>
      </Card>
    );
  };

  const renderActiveElementItems = () => {
    const filteredCards = cardsData.filter((card) => card.elementName === activeElement);
    const itemCount = Math.min(filteredCards.length, maxItems);

    const rows = [0, 1].map((rowIndex) => (
      <Row className="custom-row" key={`row-${rowIndex}`}>
        {Array(3)
          .fill(null)
          .map((_, colIndex) => {
            if (rowIndex === 1 && colIndex === 2) {
              return <Col key={`col-${rowIndex}-${colIndex}`}></Col>;
            }
            const cardIndex = rowIndex * 3 + colIndex;
            if (cardIndex < itemCount) {
              return (
                <Col key={`col-${rowIndex}-${colIndex}`}>
                  {renderCard(cardIndex, activeElement)}
                </Col>
              );
            }
            return <Col key={`col-${rowIndex}-${colIndex}`}></Col>;
          })}
      </Row>
    ));

    return rows;
  };

  const renderShelves = () => {
    const shelves = [];
    const maxShelves = 5;
    for (let i = 0; i < numberOfShelves; i++) {
      shelves.push(
        <div
          key={`shelf-${i}`}
          className={`shelf ${activeElement === `shelf-${i}` ? "active-element" : ""}`}
          style={{ cursor: "pointer" }}
          onClick={() => {
            setShowDetails(true);
            setActiveElement(`shelf-${i}`);
          }}
        ></div>
      );
    }

    if (numberOfShelves < maxShelves) {
      shelves.push(
        <div
          key="big-square"
          className="big-square"
        ></div>
      );
    }
    return shelves;
  };

  const renderHangers = () => {
    const hangers = [];
    for (let i = 0; i < numberOfHangers; i++) {
      hangers.push(
        <div
          key={`hanger-${i}`}
          className={`hanger${i + 1} ${activeElement === `hanger-${i}` ? "active-element" : ""}`}
          style={{ cursor: "pointer" }}
          onClick={() => {
            setShowDetails(true);
            setActiveElement(`hanger-${i}`);
          }}
        ></div>
      );
    }
    return hangers;
  };

  const renderModalDrawers = () => {
    const modalDrawers = [];
    for (let i = 0; i < numberOfDrawers; i++) {
      modalDrawers.push(
        <div
          key={`drawer-${i}`}
          className={`modal-drawer1 ${activeElement === `drawer-${i}` ? "active-element" : ""}`}
          style={{ cursor: "pointer" }}
          onClick={() => {
            setShowDetails(true);
            setActiveElement(`drawer-${i}`);
          }}
        >
          <div className="modal-handle"></div>
        </div>
      );
    }
    return modalDrawers;
  };

  const handleNavigationButtonClick = () => {
    setShowDetails(false);
    setActiveElement(null);
    setActiveCardIndex(null);
    setChecked(false);
  };

  return (
    <div onClick={handlePageClick} style={{ height: "100%" }}>
      <Modal
        className="closet-modal"
        show={show}
        onHide={handleClose}
        centered
        fullscreen
        animation
        backdrop="static"
        keyboard={false}
      >
        <Container fluid className="closet-container-modal">
          <div className="modal-header-closet">
            <h2 className="modal-name">{name}</h2>
            <div className="modal-button">
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
            </div>
          </div>

          {!showDetails ? (
            <div className="just-closet-without-articles">
              <div className="modal-second-row">
                <div className="modal-shelves">{renderShelves()}</div>
                <div className="modal-hangers">{renderHangers()}</div>
              </div>
              <div className="modal-third-row">{renderModalDrawers()}</div>
            </div>
          ) : (
            <div className="sec-and-third">
              <div className="just-closet">
                <div className="modal-second-row">
                  <div className="modal-shelves">{renderShelves()}</div>
                  <div className="modal-hangers">{renderHangers()}</div>
                </div>
                <div className="modal-third-row">{renderModalDrawers()}</div>
              </div>
              <div className="modal-articles d-flex flex-column align-items-stretch">
                <Container className="articles-background p-3">
                  <div className="navigation-button-wrapper-closet">
                    <Button
                      className="navigation-button-closet left"
                      onClick={handleNavigationButtonClick}
                    >
                      &#8249;
                    </Button>
                  </div>
                  {renderActiveElementItems()}
                </Container>
                <div className="closet-button-and-share">
                  <Button
                    className="add-item-closet"
                    onClick={handleAddItemClick}
                    disabled={cardsData.filter(card => card.elementName === activeElement).length >= maxItems}
                  >
                    Add Item
                  </Button>
                  <Form>
                    <Form.Check
                      type="checkbox"
                      id="checkbox1"
                      label="Share your article!"
                      checked={checked}
                      onChange={handleCheckboxChange}
                      disabled={activeCardIndex === null}
                    />
                  </Form>
                </div>
              </div>
            </div>
          )}
        </Container>
      </Modal>

      <AddAdvertisementPopupCloset
        showModal={showModal}
        handleClose={() => setShowModal(false)}
        handleAddItemCloset={handleAddItemCloset} // Pass handleAddItemCloset as a prop
      />
    </div>
  );
};

export default ClosetPreview;
