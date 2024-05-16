export class Menetlus {
  id: number = 0;
  name: string;
  personalCode: number;
  email: string;
  emailDelivered: boolean = false;
  reason: string;

  constructor(
    name: string,
    personalCode: number,
    email: string,
    reason: string
  ) {
    this.name = name;
    this.personalCode = personalCode;
    this.email = email;
    this.reason = reason;
  }
}
