import axios from 'axios';
import { TrackingResponse, GpsTrackRequest } from '../types/tracking';

const API_URL = import.meta.env.VITE_API_URL || 'http://localhost:8080/api/v1';

export const trackingService = {
  trackVehicle: (data: GpsTrackRequest) =>
    axios.post<TrackingResponse>(`${API_URL}/gps/track`, data),

  getTracking: (shipmentId: string, companyId: number) =>
    axios.get<TrackingResponse>(`${API_URL}/gps/tracking/${shipmentId}`, {
      params: { companyId },
    }),

  // WebSocket subscription helper (integra com STOMP existente)
  subscribeToTracking: (shipmentId: string, callback: (update: any) => void) => {
    // Implementação com STOMP client já existente no projeto
    console.log(`Subscribing to tracking updates for ${shipmentId}`);
  }
};