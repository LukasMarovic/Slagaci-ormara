import { useState } from "react";
import { Container, Modal, Button, Card, Row, Col } from "react-bootstrap";
import "../CustomCss/Closet.css";

const ClosetPreview = ({ show, handleClose, closet }) => {
  if (!closet) return null;

  const { name, numberOfDrawers, numberOfShelves, numberOfHangers } = closet;
  const [showDetails, setShowDetails] = useState(false); // State to toggle detailed view
  const [activeElement, setActiveElement] = useState(null); // Tracks the currently selected element
  const [elementItems, setElementItems] = useState({}); // Tracks items for each element
  const [preRenderedRows, setPreRenderedRows] = useState(null);

  const maxItems = 5; // Maximum number of items allowed

  // Sample data for cards
  const cardData = [
    { title: "Card 1", text: "Lorem ipsum 1", img: "public/assets/images/shirt_placeholder.jpg" },
    { title: "Card 2", text: "Lorem ipsum 2", img: "public/assets/images/shirt_placeholder.jpg" },
    { title: "Card 3", text: "Lorem ipsum 3", img: "public/assets/images/shirt_placeholder.jpg" },
    { title: "Card 4", text: "Lorem ipsum 4", img: "public/assets/images/shirt_placeholder.jpg" },
    { title: "Card 5", text: "Lorem ipsum 5", img: "public/assets/images/shirt_placeholder.jpg" },
  ];

  // Function to render card content dynamically
  const renderCard = (index) => (
    <Card className="custom-card" key={index}>
      <Card.Img variant="top" src={cardData[index % cardData.length].img} alt={`Card ${index + 1}`} />
      <Card.Body className="card-body-closet">
        <Card.Title className="custom-title">{cardData[index % cardData.length].title}</Card.Title>
        <Card.Text className="custom-text">{cardData[index % cardData.length].text}</Card.Text>
      </Card.Body>
    </Card>
  );

 
  const handleAddItem = () => {
    if (activeElement !== null) {
      setElementItems((prev) => {
        const currentCount = prev[activeElement] || 0;
        if (currentCount < maxItems) {
          return { ...prev, [activeElement]: currentCount + 1 };
        }
        return prev; 
      });
    }
  };

  const renderActiveElementItems = () => {
    const itemCount = Math.min(elementItems[activeElement] || 0, maxItems); // Ensure items do not exceed the limit

    // Pre-render two rows with three columns each
    const rows = [0, 1].map((rowIndex) => (
      <Row className="custom-row" key={`row-${rowIndex}`}>
        {Array(3)
          .fill(null)
          .map((_, colIndex) => {
            // Avoid rendering content in the last column of the second row
            if (rowIndex === 1 && colIndex === 2) {
              return <Col key={`col-${rowIndex}-${colIndex}`}></Col>;
            }
            const cardIndex = rowIndex * 3 + colIndex;
            if (cardIndex < itemCount) {
              return <Col key={`col-${rowIndex}-${colIndex}`}>{renderCard(cardIndex)}</Col>;
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
  };

  return (
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
        {/* Header */}
        <div className="modal-header-closet">
          <h2 className="modal-name">{name}</h2>
          <div className="modal-button">
            <Button variant="secondary" onClick={handleClose}>
              Close
            </Button>
          </div>
        </div>

        {/* Conditionally render either simple view or detailed view */}
        {!showDetails ? (
          <>
            <div className="just-closet-without-articles">
              <div className="modal-second-row">
                <div className="modal-shelves">{renderShelves()}</div>
                <div className="modal-hangers">{renderHangers()}</div>
              </div>
              <div className="modal-third-row">{renderModalDrawers()}</div>
            </div>
          </>
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
              <Button
                className="add-item-closet"
                onClick={handleAddItem} // Updated to use this button for adding items
              >
                Add Item
              </Button>
            </div>
          </div>
        )}
      </Container>
    </Modal>
  );
};

export default ClosetPreview;
