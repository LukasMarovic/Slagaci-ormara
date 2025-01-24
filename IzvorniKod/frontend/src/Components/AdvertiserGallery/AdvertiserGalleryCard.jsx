/* eslint-disable no-unused-vars */
import 'bootstrap/dist/css/bootstrap.min.css';
import { useState } from 'react';
import Card from 'react-bootstrap/Card';
import Col from 'react-bootstrap/Col';
import Row from 'react-bootstrap/Row';
import Pagination from 'react-bootstrap/Pagination';
import { FaArrowLeft } from 'react-icons/fa';
import { FaArrowRight } from 'react-icons/fa';
import ArticleCardDetails from '../ArticleCardDetails';

function AdvertiserGalleryCard() {
    const [articles, setArticles] = useState([
        { 
            articleName: "Article 1", 
            description: "Description 1", 
            price: "100", 
            image: "/public/vite.svg", 
            coverage: "Open", 
            season: "Summer", 
            formality: "Casual", 
            primaryColor: "Black", 
            secondaryColor: "White",
            category: "T-Shirts",
            condition: "New"
        },
        { 
            articleName: "Article 2", 
            description: "Description 2", 
            price: "120", 
            image: "/public/vite.svg", 
            coverage: "Closed", 
            season: "Winter", 
            formality: "Formal", 
            primaryColor: "Blue", 
            secondaryColor: "Grey",
            category: "Sweaters",
            condition: "Used"
        },
    ]);

    const [active, setActive] = useState(1);
    const [selectedArticle, setSelectedArticle] = useState({});
    const [showPreviewBool, setShowPreviewBool] = useState(false);

    const articles_per_page = 8;
    const pageCount = Math.ceil(articles.length / articles_per_page);

    const displayedArticles = articles.slice((active - 1) * articles_per_page, active * articles_per_page);

    let pagination_items = [];

    if (pageCount <= 5) {
        for (let i = 1; i <= pageCount; i++) {
            pagination_items.push(
                <Pagination.Item
                    key={i}
                    active={active === i}
                    onClick={() => setActive(i)}
                >
                    {i}
                </Pagination.Item>
            );
        }
    } else {
        if (active <= 3) {
            for (let i = 1; i <= 3; i++) {
                pagination_items.push(
                    <Pagination.Item
                        key={i}
                        active={active === i}
                        onClick={() => setActive(i)}
                    >
                        {i}
                    </Pagination.Item>
                );
            }
            pagination_items.push(<Pagination.Ellipsis key="ellipsis1" />);
            pagination_items.push(
                <Pagination.Item
                    key={pageCount}
                    onClick={() => setActive(pageCount)}
                >
                    {pageCount}
                </Pagination.Item>
            );
        } else if (active >= pageCount - 2) {
            pagination_items.push(
                <Pagination.Item
                    key={1}
                    onClick={() => setActive(1)}
                >
                    1
                </Pagination.Item>
            );
            pagination_items.push(<Pagination.Ellipsis key="ellipsis1" />);
            for (let i = pageCount - 2; i <= pageCount; i++) {
                pagination_items.push(
                    <Pagination.Item
                        key={i}
                        active={active === i}
                        onClick={() => setActive(i)}
                    >
                        {i}
                    </Pagination.Item>
                );
            }
        } else {
            pagination_items.push(
                <Pagination.Item
                    key={1}
                    onClick={() => setActive(1)}
                >
                    1
                </Pagination.Item>
            );
            pagination_items.push(<Pagination.Ellipsis key="ellipsis1" />);
            for (let i = active - 1; i <= active + 1; i++) {
                pagination_items.push(
                    <Pagination.Item
                        key={i}
                        active={active === i}
                        onClick={() => setActive(i)}
                    >
                        {i}
                    </Pagination.Item>
                );
            }
            pagination_items.push(<Pagination.Ellipsis key="ellipsis2" />);
            pagination_items.push(
                <Pagination.Item
                    key={pageCount}
                    onClick={() => setActive(pageCount)}
                >
                    {pageCount}
                </Pagination.Item>
            );
        }
    }

    const handleClick = (article) => {
        setSelectedArticle(article);
        setShowPreviewBool(true);
    };

    const pagination_leading_component = (
        <>
            <Pagination.First key={"first"} onClick={() => setActive(1)}>
                <FaArrowLeft className="pagination-icon" />
            </Pagination.First>
            <Pagination.Prev key={"prev"} onClick={() => setActive(Math.max(active - 1, 1))}>
                Previous
            </Pagination.Prev>
        </>
    );

    const pagination_trailing_component = (
        <>
            <Pagination.Next key={"next"} onClick={() => setActive(Math.min(active + 1, pageCount))}>
                Next
            </Pagination.Next>
            <Pagination.Last key={"last"} onClick={() => setActive(pageCount)}>
                <FaArrowRight className="pagination-icon" />
            </Pagination.Last>
        </>
    );

    return (
        <>
            <Row xs={1} sm={2} md={3} lg={4} className="card-container g-3">
                {displayedArticles.map((article, idx) => (
                    <Col key={idx}>
                        <Card className="article-card" onClick={() => handleClick(article)}>
                            <Card.Img variant="top" src={article.image} className="article-card-image" />
                            <Card.Body className="article-card-body">
                                <Card.Title className="article-card-title">{article.articleName}</Card.Title>
                                <Card.Text className="article-card-text">{article.description}</Card.Text>
                                <Card.Text className="article-card-text">Price: {article.price}</Card.Text>
                            </Card.Body>
                        </Card>
                    </Col>
                ))}
            </Row>

            <div className="d-flex justify-content-center mt-5">
                {pageCount !== 0
                    ? pageCount > 2
                        ? <Pagination>{pagination_leading_component}{pagination_items}{pagination_trailing_component}</Pagination>
                        : <Pagination>{pagination_items}</Pagination>
                    : <></>
                }
            </div>
            <ArticleCardDetails
                show={showPreviewBool}
                handleClose={() => setShowPreviewBool(false)}
                cardData={selectedArticle}
            >
            </ArticleCardDetails>
        </>
    );
}

export default AdvertiserGalleryCard;
