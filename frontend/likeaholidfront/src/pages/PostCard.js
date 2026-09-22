import React from 'react';
import { Button, Card, Col, Row } from "react-bootstrap";
import Container from "react-bootstrap/Container";

let url = "http://localhost:8080/";

export default function PostCard({ post, onLike, onEdit, onDelete }) {
    return (
        <Container fluid>
            <Row className="mb-5 d-flex justify-content-center">
                <Col xs={12} md={10} lg={8} key={post.postId}> {/* Adjust column width */}
                    <Card style={{ width: '100%', padding: '1rem', borderRadius: '8px', boxShadow: '0 2px 5px rgba(0, 0, 0, 0.2)' }}>
                        <Card.Img
                            variant="top"
                            src={url + post.imageUrl}
                            style={{ width: '100%', height: '200px', objectFit: 'cover', borderRadius: '4px' }}
                        />
                        <Card.Body>
                            <Card.Title style={{ fontSize: '1.5rem', marginBottom: '1rem' }}>{post.title}</Card.Title>
                            <Card.Text style={{ fontSize: '1rem', marginBottom: '1rem' }}>{post.content}</Card.Text>
                            <Card.Text style={{ fontSize: '0.9rem', color: 'gray', marginBottom: '1.5rem' }}>Posted: {post.createdAt}</Card.Text>
                            <div className="d-flex justify-content-between">
                                <Button
                                    variant={post.isLiked ? "primary" : "outline-primary"}
                                    size="sm"
                                    onClick={() => onLike(post)}
                                >
                                    ❤️ {post.likesCount > 0 ? `(${post.likesCount})` : ''}
                                </Button>
                                <Button variant="info" onClick={() => onEdit(post)}>Edit</Button>
                                <Button variant="danger" onClick={() => onDelete(post.postId)}>Delete</Button>
                            </div>
                        </Card.Body>
                    </Card>
                </Col>
            </Row>
        </Container>
    );
}