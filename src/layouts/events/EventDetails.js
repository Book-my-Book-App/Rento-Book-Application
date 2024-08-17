import React from 'react';
import { FaMapMarkerAlt, FaCalendarAlt, FaArrowLeft } from 'react-icons/fa';
import './EventDetails.css';

const EventDetails = ({ event, onBackClick }) => {
  const [imageSrc, setImageSrc] = React.useState('');

  React.useEffect(() => {
    fetch(`http://localhost:8080/api/events/image/${event.id}`)
      .then((response) => response.blob())
      .then((blob) => {
        const url = URL.createObjectURL(blob);
        setImageSrc(url);
      });
  }, [event.id]);

  return (
    <div className="event-details">
      <button className="back-button" onClick={onBackClick}><FaArrowLeft /> Back</button>
      <div className="event-details-content">
        {imageSrc && <img src={imageSrc} alt={event.name} className="event-details-image" />}
        <div className="event-description">
          <h2>{event.name}</h2>
          <p className="event-date"><FaCalendarAlt /> {event.date}</p>
          <p className="event-address"><FaMapMarkerAlt /> {event.address}</p>
          <div className="event-text">
            <p>{event.description}</p>
          </div>
          <button className="reserve-button">Reserve a Seat</button>
        </div>
      </div>
    </div>
  );
};

export default EventDetails;
