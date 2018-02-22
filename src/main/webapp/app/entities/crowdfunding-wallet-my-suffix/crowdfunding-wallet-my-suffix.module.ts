import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';
import { RouterModule } from '@angular/router';

import { NuoSharedModule } from '../../shared';
import {
    CrowdfundingWalletMySuffixService,
    CrowdfundingWalletMySuffixPopupService,
    CrowdfundingWalletMySuffixComponent,
    CrowdfundingWalletMySuffixDetailComponent,
    CrowdfundingWalletMySuffixDialogComponent,
    CrowdfundingWalletMySuffixPopupComponent,
    CrowdfundingWalletMySuffixDeletePopupComponent,
    CrowdfundingWalletMySuffixDeleteDialogComponent,
    crowdfundingWalletRoute,
    crowdfundingWalletPopupRoute,
} from './';

const ENTITY_STATES = [
    ...crowdfundingWalletRoute,
    ...crowdfundingWalletPopupRoute,
];

@NgModule({
    imports: [
        NuoSharedModule,
        RouterModule.forChild(ENTITY_STATES)
    ],
    declarations: [
        CrowdfundingWalletMySuffixComponent,
        CrowdfundingWalletMySuffixDetailComponent,
        CrowdfundingWalletMySuffixDialogComponent,
        CrowdfundingWalletMySuffixDeleteDialogComponent,
        CrowdfundingWalletMySuffixPopupComponent,
        CrowdfundingWalletMySuffixDeletePopupComponent,
    ],
    entryComponents: [
        CrowdfundingWalletMySuffixComponent,
        CrowdfundingWalletMySuffixDialogComponent,
        CrowdfundingWalletMySuffixPopupComponent,
        CrowdfundingWalletMySuffixDeleteDialogComponent,
        CrowdfundingWalletMySuffixDeletePopupComponent,
    ],
    providers: [
        CrowdfundingWalletMySuffixService,
        CrowdfundingWalletMySuffixPopupService,
    ],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NuoCrowdfundingWalletMySuffixModule {}
