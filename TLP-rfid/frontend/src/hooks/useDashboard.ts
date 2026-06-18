import { useEffect, useState } from "react";
import { getDashboard } from "../services/dashboardService";

export function useDashboard() {
  const [data, setData] = useState(null);
  const [loading, setLoading] = useState(true);
  const [error, setError] = useState<string | null>(null);

  useEffect(() => {
    getDashboard()
      .then(setData)
      .catch((err) => {
        console.error("Erro ao carregar dashboard:", err);
        setError(err?.message ?? "Erro desconhecido");
      })
      .finally(() => setLoading(false));
  }, []);

  return { data, loading, error };
}
