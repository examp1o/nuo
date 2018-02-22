import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NuoSharedModule } from '../../shared';
import {
    InvestmentMySuffixService,
    InvestmentMySuffixPopupService,
    InvestmentMySuffixComponent,
    InvestmentMySuffixDetailComponent,
    InvestmentMySuffixDialogComponent,
    InvestmentMySuffixPopupComponent,
    InvestmentMySuffixDeletePopupComponent,
    InvestmentMySuffixDeleteDialogComponent,
    investmentRoute,
    investmentPopupRoute,
    InvestmentMySuffixResolvePagingParams,
} from './';

const ENTITY_STATES = [
    ...investmentRoute,
    ...investmentPopupRoute,
];

@NgModule({
    imports: [
        NuoSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        InvestmentMySuffixComponent,
        InvestmentMySuffixDetailComponent,
        InvestmentMySuffixDialogComponent,
        InvestmentMySuffixDeleteDialogComponent,
        InvestmentMySuffixPopupComponent,
        InvestmentMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        InvestmentMySuffixComponent,
        InvestmentMySuffixDialogComponent,
        InvestmentMySuffixPopupComponent,
        InvestmentMySuffixDeleteDialogComponent,
        InvestmentMySuffixDeletePopupComponent,
    ],
    providers: [
        InvestmentMySuffixService,
        InvestmentMySuffixPopupService,
        InvestmentMySuffixResolvePagingParams,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NuoInvestmentMySuffixModule {}
