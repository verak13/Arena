import { UserRole } from "./user-role";

export class LogInModel {
  constructor(
    private email: string,
    private accessToken: string,
    private id: number,
    private role: string
  ) {
  }

  getRole(): UserRole {
    return this.role === 'ROLE_ADMINISTRATOR' ? UserRole.ROLE_ADMINISTRATOR : (this.role === 'ROLE_USER' ? UserRole.ROLE_USER : UserRole.UNAUTHORIZED);
  }
}
