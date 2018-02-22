import { BaseEntity } from './../../shared';

export class BulletinMySuffix implements BaseEntity {
    constructor(
        public id?: number,
        public content?: string,
        public user?: number,
        public createTime?: number,
    ) {
    }
}
