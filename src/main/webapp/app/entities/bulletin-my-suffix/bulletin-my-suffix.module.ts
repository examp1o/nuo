import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NuoSharedModule } from '../../shared';
import {
    BulletinMySuffixService,
    BulletinMySuffixPopupService,
    BulletinMySuffixComponent,
    BulletinMySuffixDetailComponent,
    BulletinMySuffixDialogComponent,
    BulletinMySuffixPopupComponent,
    BulletinMySuffixDeletePopupComponent,
    BulletinMySuffixDeleteDialogComponent,
    bulletinRoute,
    bulletinPopupRoute,
    BulletinMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...bulletinRoute,
    ...bulletinPopupRoute,
];

@NgModule({
    imports: [
        NuoSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        BulletinMySuffixComponent,
        BulletinMySuffixDetailComponent,
        BulletinMySuffixDialogComponent,
        BulletinMySuffixDeleteDialogComponent,
        BulletinMySuffixPopupComponent,
        BulletinMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        BulletinMySuffixComponent,
        BulletinMySuffixDialogComponent,
        BulletinMySuffixPopupComponent,
        BulletinMySuffixDeleteDialogComponent,
        BulletinMySuffixDeletePopupComponent,
    ],
    providers: [
        BulletinMySuffixService,
        BulletinMySuffixPopupService,
        BulletinMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NuoBulletinMySuffixModule {}
