import { BaseEntity } from './../../shared';

export class InvestmentMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public amount?: number,
        public sourceAddress?: string,
        public investmentTime?: number,
        public user?: number,
        public projectId?: number,
    ) {
    }
}
