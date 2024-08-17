import React, { useState } from 'react';
import { useNavigate } from 'react-router-dom';
import axios from 'axios';
import 'bootstrap/dist/css/bootstrap.min.css';
import './AddEventForm.css'; // Ensure custom styles are compatible with Bootstrap

const AddEventForm = ({ onEventAdded }) => {
  const [name, setName] = useState('');
  const [date, setDate] = useState('');
  const [address, setAddress] = useState('');
  const [description, setDescription] = useState('');
  const [image, setImage] = useState(null);
  const navigate = useNavigate();

  const handleSubmit = async (e) => {
    e.preventDefault();

    const formData = new FormData();
    formData.append('name', name);
    formData.append('date', date);
    formData.append('address', address);
    formData.append('description', description);
    if (image) formData.append('image', image);

    try {
      const response = await axios.post('http://localhost:8080/api/events', formData, {
        headers: { 'Content-Type': 'multipart/form-data' }
      });
      const newEvent = response.data;
      onEventAdded(newEvent);
      navigate('/');
    } catch (error) {
      console.error("There was an error adding the event!", error);
    }
  };

  return (
    <div className="container mt-5">
      <h2 className="mb-4 text-center">Add New Event</h2>
      <form className="needs-validation" onSubmit={handleSubmit} noValidate>
        <div className="mb-3">
          <label htmlFor="name" className="form-label">Name:</label>
          <input 
            type="text" 
            id="name" 
            className="form-control" 
            value={name} 
            onChange={(e) => setName(e.target.value)} 
            required 
          />
          <div className="invalid-feedback">
            Please enter the event name.
          </div>
        </div>
        <div className="mb-3">
          <label htmlFor="date" className="form-label">Date:</label>
          <input 
            type="date" 
            id="date" 
            className="form-control" 
            value={date} 
            onChange={(e) => setDate(e.target.value)} 
            required 
          />
          <div className="invalid-feedback">
            Please select a date for the event.
          </div>
        </div>
        <div className="mb-3">
          <label htmlFor="address" className="form-label">Address:</label>
          <input 
            type="text" 
            id="address" 
            className="form-control" 
            value={address} 
            onChange={(e) => setAddress(e.target.value)} 
            required 
          />
          <div className="invalid-feedback">
            Please enter the event address.
          </div>
        </div>
        <div className="mb-3">
          <label htmlFor="description" className="form-label">Description:</label>
          <textarea 
            id="description" 
            className="form-control" 
            value={description} 
            onChange={(e) => setDescription(e.target.value)} 
            required 
          ></textarea>
          <div className="invalid-feedback">
            Please provide a description of the event.
          </div>
        </div>
        <div className="mb-3">
          <label htmlFor="image" className="form-label">Image:</label>
          <input 
            type="file" 
            id="image" 
            className="form-control" 
            onChange={(e) => setImage(e.target.files[0])} 
            required 
          />
          <div className="invalid-feedback">
            Please upload an image for the event.
          </div>
        </div>
        <div className="d-flex justify-content-between">
          <button type="submit" className="btn btn-primary">Add Event</button>
          <button type="button" className="btn btn-secondary" onClick={() => navigate('/')}>Back to Home</button>
        </div>
      </form>
    </div>
  );
};

export default AddEventForm;
