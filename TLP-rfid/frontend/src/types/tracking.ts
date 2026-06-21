export interface GpsCoordinate {
  latitude: number;
  longitude: number;
}

export interface TrackingEvent {
  id: string;
  companyId: number;
  shipmentId: string;
  vehicleId: string;
  rfidTag?: string;
  eventType: 'RFID_READ' | 'GPS_UPDATE' | 'STATUS_CHANGE' | 'ALERT' | 'ROUTE_DEVIATION';
  coordinate?: GpsCoordinate;
  speed?: number;
  locationDescription?: string;
  eventTime: string;
  additionalData?: any;
}

export interface PredictionInfo {
  predictedEta: string;
  confidence: number;
  routeStatus: 'ON_TIME' | 'DELAYED' | 'AHEAD';
  estimatedDelayMinutes: number;
  explanation: string;
}

export interface TrackingResponse {
  shipmentId: string;
  vehicleId: string;
  currentStatus: string;
  lastUpdate: string;
  prediction?: PredictionInfo;
  recentEvents: TrackingEvent[];
}

export interface GpsTrackRequest {
  vehicleId: string;
  shipmentId: string;
  companyId: number;
}