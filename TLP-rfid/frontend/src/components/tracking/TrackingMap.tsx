import { MapContainer, TileLayer, Marker, Popup, Polyline } from 'react-leaflet';
import { TrackingEvent } from '../../types/tracking';
import L from 'leaflet';
import 'leaflet/dist/leaflet.css';

// Fix for default markers
delete (L.Icon.Default.prototype as any)._getIconUrl;
L.Icon.Default.mergeOptions({
  iconRetinaUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-icon-2x.png',
  iconUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-icon.png',
  shadowUrl: 'https://cdnjs.cloudflare.com/ajax/libs/leaflet/1.9.4/images/marker-shadow.png',
});

interface Props {
  events: TrackingEvent[];
}

export const TrackingMap = ({ events }: Props) => {
  const sortedEvents = [...events].sort((a, b) => 
    new Date(b.eventTime).getTime() - new Date(a.eventTime).getTime()
  );

  const positions = sortedEvents
    .filter(e => e.coordinate)
    .map(e => [e.coordinate!.latitude, e.coordinate!.longitude] as [number, number]);

  if (positions.length === 0) {
    return <div className="h-96 flex items-center justify-center bg-gray-100">Sem dados de localização GPS</div>;
  }

  return (
    <MapContainer center={positions[0]} zoom={12} className="h-96 w-full rounded-lg">
      <TileLayer
        url="https://{s}.tile.openstreetmap.org/{z}/{x}/{y}.png"
        attribution='&copy; OpenStreetMap'
      />
      <Polyline positions={positions} color="blue" weight={4} />
      {sortedEvents.map((event, idx) => (
        event.coordinate && (
          <Marker key={idx} position={[event.coordinate.latitude, event.coordinate.longitude]}>
            <Popup>
              <strong>{event.eventType.replace('_', ' ')}</strong><br />
              {event.locationDescription}<br />
              Velocidade: {event.speed} km/h<br />
              {new Date(event.eventTime).toLocaleString('pt-BR')}
            </Popup>
          </Marker>
        )
      ))}
    </MapContainer>
  );
};