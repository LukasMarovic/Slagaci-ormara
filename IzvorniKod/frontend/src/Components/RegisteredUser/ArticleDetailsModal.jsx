import { useState } from "react";
import { Modal, Button, Form, Row, Col } from "react-bootstrap";
import "./registered_css/ArticleDetails.css";

const ArticleDetailsModal = ({ show, handleClose, cardData, onCardUpdate, onForSharingChange }) => {
    if (!cardData) return null;
  
    const [formData, setFormData] = useState({ ...cardData });
    const [isEditing, setIsEditing] = useState(false);
  
    const handleChange = (field, value) => {
      setFormData({ ...formData, [field]: value });
    };
  
    const handleSubmit = () => {
      onCardUpdate(formData); // Send updated form data back to parent
      setIsEditing(false); // Exit editing mode
    };
  
    const handleDiscard = () => {
      setFormData({ ...cardData }); // Reset to original card data
      setIsEditing(false);
    };
  
    const handleSwitchChange = () => {
      const updatedForSharing = !formData.forSharing;
      setFormData({ ...formData, forSharing: updatedForSharing });
      onForSharingChange(cardData, updatedForSharing); // Update parent directly for `forSharing`
    };
  
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
              alt={formData.title}
              style={{
                maxWidth: "100%",
                height: "auto",
                objectFit: "cover",
                maxHeight: "700px",
              }}
            />
          </Col>
          <Col md={6} className="article-details position-relative">
            {isEditing ? (
            <Form>
                <Form.Group controlId="formArticleName" className="mb-3">
                <Form.Label className="modal-label">Article Name</Form.Label>
                <Form.Control
                    type="text"
                    value={formData.title}
                    onChange={(e) => handleChange("title", e.target.value)}
                />
                </Form.Group>
                <Form.Group controlId="formCategory" className="mb-3">
                <Form.Label className="modal-label">Category</Form.Label>
                <Form.Select
                    value={formData.category}
                    onChange={(e) => handleChange("category", e.target.value)}
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
                <Form.Group controlId="formSeason" className="mb-3">
                <Form.Label className="modal-label">Season</Form.Label>
                <Form.Select
                    value={formData.season}
                    onChange={(e) => handleChange("season", e.target.value)}
                >
                    <option value="" disabled>Select Season</option>
                    <option value="Spring">Spring</option>
                    <option value="Summer">Summer</option>
                    <option value="Fall">Fall</option>
                    <option value="Winter">Winter</option>
                </Form.Select>
                </Form.Group>
                <Form.Group controlId="formColor" className="mb-3">
                <Form.Label className="modal-label">Color</Form.Label>
                <Form.Select
                    value={formData.color}
                    onChange={(e) => handleChange("color", e.target.value)}
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
                    value={formData.secondaryColor || ""}
                    onChange={(e) => handleChange("secondaryColor", e.target.value)}
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
                    value={formData.formality || ""}
                    onChange={(e) => handleChange("formality", e.target.value)}
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
                    value={formData.condition || ""}
                    onChange={(e) => handleChange("condition", e.target.value)}
                >
                                <option value="" disabled>
                                Select Condition
                            </option>
                            <option value="New">New</option>
                            <option value="Used">Used</option>
                </Form.Select>
                </Form.Group>
                <Form.Group controlId="formDescription" className="mb-3">
                <Form.Label className="modal-label">Description</Form.Label>
                <Form.Control
                    as="textarea"
                    rows={3}
                    value={formData.description || ""}
                    onChange={(e) => handleChange("description", e.target.value)}
                />
                </Form.Group>

                <Button variant="success" onClick={handleSubmit} className="me-2">
                Submit
                </Button>
                <Button variant="secondary" onClick={handleDiscard}>
                Discard
                </Button>
            </Form>
            )  : (
                <>
                <Button
                  variant="primary"
                  size="md"
                  className="edit-button"
                  onClick={() => setIsEditing(true)}
                >
                  Edit
                </Button>
                <div style={{ overflowY: "auto", height: "100%" }}>
                  <h2>{formData.title}</h2>
                  <p><strong>Category:</strong> {formData.category}</p>
                  <p><strong>Season:</strong> {formData.season}</p>
                  <p><strong>Color:</strong> {formData.color}</p>
                  <p><strong>Secondary Color:</strong> {formData.secondaryColor || "N/A"}</p>
                  <p><strong>Formality:</strong> {formData.formality}</p>
                  <p><strong>Condition:</strong> {formData.condition}</p>
                  <p><strong>Description:</strong> {formData.description}</p>
                  <div className="d-flex flex-row align-items-center">
                    <label className="toggle-switch">
                      <input
                        type="checkbox"
                        checked={formData.forSharing}
                        onChange={handleSwitchChange}
                      />
                      <div className="toggle-switch-background">
                        <div className="toggle-switch-handle"></div>
                      </div>
                    </label>
                    <div style={{ paddingLeft: "10px" }}>Share your article!</div>
                  </div>
                </div>
              </>

            )}
          </Col>
        </Row>
      </Modal.Body>
      <Modal.Footer>
        <Button variant="secondary" onClick={handleClose}>
          Close
        </Button>
      </Modal.Footer>
    </Modal>
  );
};

export default ArticleDetailsModal;
