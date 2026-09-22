import {useEffect, useState} from "react";
import {Button} from "react-bootstrap";

export default function PostForm(props) {
    const { post, onSave, isAdding } = props;
    const [formData,setFormData] = useState({
            postId: '',
            content: '',
            imageUrl: '',
            title: '',
            createdAt: '',
            userId: ''
        }
    )

    useEffect(()=>{
        if(post){
            console.log("Setting form data:", post);
            setFormData({
                postId: post.postId || '',
                content: post.content || '',
                imageUrl: post.imageUrl || '',
                title: post.title || '',
                createdAt: post.createdAt ||'',
                userId: post.user_id || ''
            });
        }
    }, [post]);
    const handleChange = (e) => {
        const { name, value } = e.target;
        setFormData((prev) => ({...prev, [name]: value}))
    }

    const handleSubmit = (e) => {
        e.preventDefault();
        console.log("Form data before saving:", formData);

        const postData = {
            ...formData,
            createdAt: formData.createdAt ? formData.createdAt : new Date().toISOString()
        };
        onSave(postData, post ? post.postId : null);
    };

    return(
        <div className="form-container">
            <h2>{post ? 'Edit Post' : 'Add Post'}</h2>
            <form onSubmit={handleSubmit}>
                <div className="mb-3">
                    <label>Post ID: </label>
                        <input type="text" name="postId" value={formData.postId} onChange={handleChange} readOnly={!isAdding}/>
                    </div>
                <div className="mb-3">
                    <label>Image: </label>
                    <input type="text" name="imageUrl" value={formData.imageUrl} onChange={handleChange} required/>
                </div>
                <div className="mb-3">
                    <label>Caption: </label>
                    <input type="text" name="title" value={formData.title} onChange={handleChange}/>
                </div>
                <div className="mb-3">
                    <label>Content: </label>
                    <input type="text" name="content" value={formData.content} onChange={handleChange}/>
                </div>
                <div className="mb-3">
                    <label>User ID: </label>
                    <input type="text" name="userId" value={formData.userId} onChange={handleChange} readOnly={!isAdding} required/>
                </div>

                <Button variant="primary" type="submit">
                    {post ? 'Confirm Changes' : 'Save'}
                </Button>
            </form>
        </div>
    )
}
