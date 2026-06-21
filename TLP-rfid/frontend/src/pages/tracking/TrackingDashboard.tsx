import { useState, useEffect } from 'react';
import { 
  Card, 
  Button, 
  Table, 
  Tag, 
  Typography, 
  Row, 
  Col,
  Spin 
} from 'antd';
import { TrackingMap } from '../../components/tracking/TrackingMap';
import { trackingService } from '../../services/trackingService';
import { TrackingResponse } from '../../types/tracking';

const { Title } = Typography;

export const TrackingDashboard = () => {
  const [data, setData] = useState<TrackingResponse | null>(null);
  const [loading, setLoading] = useState(false);

  const fetchTracking = async () => {
    setLoading(true);
    try {
      const res = await trackingService.getTracking("SHIP-001", 1); // TODO: tornar dinâmico
      setData(res.data);
    } catch (err) {
      console.error("Erro ao buscar tracking:", err);
    } finally {
      setLoading(false);
    }
  };

  useEffect(() => {
    fetchTracking();
  }, []);

  const columns = [
    { title: 'Tipo', dataIndex: 'eventType', key: 'type' },
    { title: 'Localização', dataIndex: 'locationDescription', key: 'location' },
    { 
      title: 'Data', 
      dataIndex: 'eventTime', 
      key: 'time', 
      render: (t: string) => new Date(t).toLocaleString('pt-BR') 
    },
  ];

  return (
    <div className="p-6 space-y-6">
      <Title level={2}>Tracking Inteligente - GPS + RFID</Title>

      <Row gutter={16}>
        <Col span={16}>
          <Card title="Mapa em Tempo Real" loading={loading}>
            {data ? (
              <TrackingMap events={data.recentEvents} />
            ) : (
              <Spin size="large" />
            )}
          </Card>
        </Col>

        <Col span={8}>
          <Card title="Resumo da Carga" loading={loading}>
            {data && (
              <>
                <p><strong>Status:</strong> <Tag color="success">{data.currentStatus}</Tag></p>
                {data.prediction && (
                  <>
                    <p><strong>ETA Previsto:</strong> {new Date(data.prediction.predictedEta).toLocaleString('pt-BR')}</p>
                    <p><strong>Confiança:</strong> {(data.prediction.confidence * 100).toFixed(0)}%</p>
                  </>
                )}
                <Button type="primary" onClick={fetchTracking} className="mt-4" block>
                  Atualizar Posição GPS
                </Button>
              </>
            )}
          </Card>
        </Col>
      </Row>

      <Card title="Histórico de Eventos" loading={loading}>
        <Table 
          dataSource={data?.recentEvents || []} 
          columns={columns} 
          rowKey="id" 
          pagination={{ pageSize: 8 }} 
        />
      </Card>
    </div>
  );
};