import api from "./api";

export interface LoginRequest { email: string; password: string; }
export interface LoginResponse { accessToken: string; tokenType?: string; }

export const authService = {
  async login(email: string, password: string): Promise<string> {
    const { data } = await api.post<LoginResponse>("/auth/login", { email, password });
    const token = data.accessToken;
    if (!token) throw new Error("Resposta de login sem accessToken");
    localStorage.setItem("token", token);
    return token;
  },

  logout() {
    localStorage.removeItem("token");
  },

  getToken(): string | null {
    return localStorage.getItem("token");
  },

  isAuthenticated(): boolean {
    return !!localStorage.getItem("token");
  },
};
