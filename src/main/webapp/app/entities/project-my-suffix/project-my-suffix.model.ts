import { BaseEntity } from './../../shared';

export const enum ProjectLevel {
    'AAA',
    'AA',
    'A',
    'BBB',
    'BB'
}

export class ProjectMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public totalAmount?: number,
        public tokensAmout?: number,
        public tokensHardTop?: number,
        public tokensName?: string,
        public exchange?: string,
        public concept?: string,
        public code?: string,
        public crowdfundingLevel?: ProjectLevel,
        public crowdfundingTarget?: number,
        public depotLock?: number,
        public remarks?: string,
        public website?: string,
        public illustration?: string,
        public grade?: number,
        public crowdfundingPlatform?: string,
        public issueDate?: number,
        public issueEndDate?: number,
        public serviceCharge?: number,
        public minCrowdfundingTarget?: number,
        public user?: number,
    ) {
    }
}
