import React, { useState, useEffect } from 'react';
import { BrowserRouter as Router, Route, Routes, Link } from 'react-router-dom';
import { Button } from 'react-bootstrap';
import 'bootstrap/dist/css/bootstrap.min.css';
import axios from 'axios';
import EventCard from './EventCard';
import EventDetails from './EventDetails';
import AddEventForm from './AddEventForm';
import EditEventForm from './EditEventForm';

const Events = () => {
  const [selectedEvent, setSelectedEvent] = useState(null);
  const [events, setEvents] = useState([]);

  // Fetch events using Axios
  useEffect(() => {
    axios.get('http://localhost:8080/api/events')
      .then(response => setEvents(response.data))
      .catch(error => console.error('There was an error fetching the events!', error));
  }, []);

  const handleCardClick = (event) => {
    setSelectedEvent(event);
  };

  const handleBackClick = () => {
    setSelectedEvent(null);
  };

  // Delete event using Axios
  const handleDelete = (id) => {
    axios.delete(`http://localhost:8080/api/events/${id}`)
      .then(() => {
        setEvents(events.filter(event => event.id !== id));
      })
      .catch(error => console.error('There was an error deleting the event!', error));
  };

  return (
    <Router>
      <div className="Events container">
        <h1 className="my-4 text-center">Event Management</h1>
        <div className="text-center mb-4">
          <Button variant="primary" as={Link} to="/add-event" className="me-2">Add Event</Button>
        </div>
        <Routes>
          <Route path="/events" element={
            <div className="row">
              {selectedEvent ? (
                <EventDetails event={selectedEvent} onBackClick={handleBackClick} />
              ) : (
                events.map(event => (
                  <div className="col-md-4 mb-4" key={event.id}>
                    <EventCard 
                      event={event} 
                      onClick={() => handleCardClick(event)} 
                    />
                    <div className="d-flex justify-content-between mt-2">
                      <Button variant="success" as={Link} to={`/edit-event/${event.id}`}>Edit</Button>
                      <Button variant="danger" onClick={() => handleDelete(event.id)}>Delete</Button>
                    </div>
                  </div>
                ))
              )}
            </div>
          } />
          <Route path="/add-event" element={<AddEventForm onEventAdded={(newEvent) => setEvents([...events, newEvent])} />} />
          <Route path="/edit-event/:id" element={<EditEventForm onEventUpdated={(updatedEvent) => setEvents(events.map(event => event.id === updatedEvent.id ? updatedEvent : event))} />} />
        </Routes>
      </div>
    </Router>
  );
};

export default Events;
