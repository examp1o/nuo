import { NgModule, CUSTOM_ELEMENTS_SCHEMA } from '@angular/core';

import { NuoBulletinMySuffixModule } from './bulletin-my-suffix/bulletin-my-suffix.module';
import { NuoProjectMySuffixModule } from './project-my-suffix/project-my-suffix.module';
import { NuoCrowdfundingWalletMySuffixModule } from './crowdfunding-wallet-my-suffix/crowdfunding-wallet-my-suffix.module';
import { NuoInvestmentMySuffixModule } from './investment-my-suffix/investment-my-suffix.module';
/* jhipster-needle-add-entity-module-import - JHipster will add entity modules imports here */

@NgModule({
    imports: [
        NuoBulletinMySuffixModule,
        NuoProjectMySuffixModule,
        NuoCrowdfundingWalletMySuffixModule,
        NuoInvestmentMySuffixModule,
        /* jhipster-needle-add-entity-module - JHipster will add entity modules here */
    ],
    declarations: [],
    entryComponents: [],
    providers: [],
    schemas: [CUSTOM_ELEMENTS_SCHEMA]
})
export class NuoEntityModule {}
