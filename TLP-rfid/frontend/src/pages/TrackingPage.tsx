import { useState, useEffect } from 'react';
import { Card, Button, Table, Tag, Typography } from 'antd';
import { TrackingMap } from '../components/tracking/TrackingMap';
import { trackingService } from '../services/trackingService';
import { TrackingResponse } from '../types/tracking';

const { Title } = Typography;

export const TrackingPage = () => {
  const [trackingData, setTrackingData] = useState<TrackingResponse | null>(null);
  const [loading, setLoading] = useState(false);

  const loadTracking = async (shipmentId: string, companyId: number) => {
    setLoading(true);
    try {
      const data = await trackingService.getTrackingHistory(shipmentId, companyId);
      setTrackingData(data);
    } catch (error) {
      console.error(error);
    } finally {
      setLoading(false);
    }
  };

  // Exemplo de uso
  useEffect(() => {
    loadTracking("SHIP-001", 1);
  }, []);

  return (
    <div className="p-6">
      <Title level={2}>Logistics Tracking - GPS + RFID</Title>

      <Card title="Mapa em Tempo Real" className="mb-6">
        {trackingData && <TrackingMap events={trackingData.recentEvents} />}
      </Card>

      <Card title="Informações da Carga">
        {trackingData && (
          <>
            <p><strong>Status:</strong> <Tag color="green">{trackingData.currentStatus}</Tag></p>
            {trackingData.prediction && (
              <p><strong>Previsão de Chegada:</strong> {new Date(trackingData.prediction.predictedEta).toLocaleString()} 
                ({trackingData.prediction.routeStatus})
              </p>
            )}
          </>
        )}
      </Card>
    </div>
  );
};