import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NuoSharedModule } from '../../shared';
import {
    ProjectMySuffixService,
    ProjectMySuffixPopupService,
    ProjectMySuffixComponent,
    ProjectMySuffixDetailComponent,
    ProjectMySuffixDialogComponent,
    ProjectMySuffixPopupComponent,
    ProjectMySuffixDeletePopupComponent,
    ProjectMySuffixDeleteDialogComponent,
    projectRoute,
    projectPopupRoute,
    ProjectMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...projectRoute,
    ...projectPopupRoute,
];

@NgModule({
    imports: [
        NuoSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        ProjectMySuffixComponent,
        ProjectMySuffixDetailComponent,
        ProjectMySuffixDialogComponent,
        ProjectMySuffixDeleteDialogComponent,
        ProjectMySuffixPopupComponent,
        ProjectMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        ProjectMySuffixComponent,
        ProjectMySuffixDialogComponent,
        ProjectMySuffixPopupComponent,
        ProjectMySuffixDeleteDialogComponent,
        ProjectMySuffixDeletePopupComponent,
    ],
    providers: [
        ProjectMySuffixService,
        ProjectMySuffixPopupService,
        ProjectMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NuoProjectMySuffixModule {}
