/* eslint-disable react/prop-types */
import { useState, useEffect } from "react";
import { Modal, Button, Row, Col } from "react-bootstrap";
import "./styles/article_details.css";

const ArticleCardDetails = ({ show, handleClose, cardData }) => {
  const [formData, setFormData] = useState(cardData);

  useEffect(() => {
    if (cardData) {
      setFormData(cardData);
    }
  }, [cardData]);

  return (
    <Modal
      show={show}
      onHide={handleClose}
      centered
      size="lg"
      backdrop="static"
      keyboard={false}
    >
      <Modal.Body className="modal-body">
        <Row>
          <Col md={6} className="d-flex justify-content-center align-items-center">
            <img
              src={formData.articlepicture}
              alt={formData.articleName}
              style={{
                maxWidth: "100%",
                height: "auto",
                objectFit: "cover",
                maxHeight: "700px",
              }}
            />
          </Col>
          <Col md={6} className="article-details position-relative">
            <div style={{ overflowY: "auto", height: "100%" }}>
              <h2>{formData.articleName}</h2>
              <p><strong>Category:</strong> {formData.category}</p>
              <p><strong>Season:</strong> {formData.seasonality}</p>
              <p><strong>Primary Color:</strong> {formData.maincolor}</p>
              <p><strong>Secondary Color:</strong> {formData.secondarycolor || "N/A"}</p>
              <p><strong>Formality:</strong> {formData.formality}</p>
              <p><strong>Condition:</strong> {formData.availability}</p>
            </div>
          </Col>
        </Row>
      </Modal.Body>
      <Modal.Footer className="d-flex justify-content-end details-modal-footer">
        <Button onClick={handleClose} className="close-button">
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ArticleCardDetails;
