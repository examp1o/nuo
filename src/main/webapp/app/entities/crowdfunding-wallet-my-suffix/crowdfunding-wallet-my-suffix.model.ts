import { BaseEntity } from './../../shared';

export const enum TokensType {
    'ETH'
}

export class CrowdfundingWalletMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public tokensType?: TokensType,
        public crowdfundingAmount?: number,
        public crowdfundingAddress?: string,
        public projectId?: number,
    ) {
    }
}
