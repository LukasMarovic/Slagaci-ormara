import { Container, Modal, Button } from "react-bootstrap";
import "../CustomCss/Closet.css";

const ClosetPreview = ({ show, handleClose, closet }) => {
  if (!closet) return null;

  const { name, numberOfDrawers, numberOfShelves, numberOfHangers } = closet;
  console.log("Closet object:", closet);

  
  const renderShelves = () => {
    const shelves = [];
    const maxShelves = 5;

    
    for (let i = 0; i < numberOfShelves; i++) {
      shelves.push(<div key={i} className="drawer"></div>);
    }

   
    if (numberOfShelves < maxShelves) {
      shelves.push(<div key="big-square" className="big-square"></div>);
    }

    return shelves;
  };

 
  const renderHangers = () => {
    const hangers = [];
    if (numberOfHangers >= 1) {
      hangers.push(<div key="hanger1" className="hanger1"></div>);
    }
    if (numberOfHangers === 2) {
      hangers.push(<div key="hanger2" className="hanger2"></div>);
    }
    return hangers;
  };

  
  const renderModalDrawers = () => {
    const modalDrawers = [];
    for (let i = 0; i < numberOfDrawers; i++) {
      modalDrawers.push(
        <div key={i} className="modal-drawer1">
          <div className="modal-handle"></div>
        </div>
      );
    }
    console.log("Rendered modal drawers:", modalDrawers); 
    return modalDrawers;
  };
  return (
    <Modal
      show={show}
      onHide={handleClose}
      centered
      fullscreen
      animation
      backdrop="static"
      keyboard={false}
    >
        <Container fluid className="closet-container-modal">
          <div className="modal-header">
          <h2 className="modal-name">{name}</h2>
          <div className="modal-button">
          <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
          </div>
          </div>
          <div className="modal-second-row">
            <div className="modal-shelves">{renderShelves()}</div>
            <div className="modal-hangers">{renderHangers()}</div>
          </div>
          <div className="modal-third-row">{renderModalDrawers()}</div>
        </Container>

    </Modal>
  );
};

export default ClosetPreview;
