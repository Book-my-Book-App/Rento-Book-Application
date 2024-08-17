import React, { useEffect, useState } from 'react';
import './EventCard.css';

const EventCard = ({ event, onClick }) => {
  const [imageSrc, setImageSrc] = useState('');

  useEffect(() => {
    fetch(`http://localhost:8080/api/events/image/${event.id}`)
      .then((response) => response.blob())
      .then((blob) => {
        const url = URL.createObjectURL(blob);
        setImageSrc(url);
      });
  }, [event.id]);

  return (
    <div className="event-card" onClick={() => onClick(event)}>
      {imageSrc && <img src={imageSrc} alt={event.name} className="event-image" />}
      <div className="event-info">
        <h2>{event.name}</h2>
        <p>Date: {event.date}</p>
        <p>Address: {event.address}</p>
      </div>
    </div>
  );
};

export default EventCard;
