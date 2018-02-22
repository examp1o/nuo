import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { CrowdfundingWalletMySuffix } from './crowdfunding-wallet-my-suffix.model';
import { CrowdfundingWalletMySuffixPopupService } from './crowdfunding-wallet-my-suffix-popup.service';
import { CrowdfundingWalletMySuffixService } from './crowdfunding-wallet-my-suffix.service';

@Component({
    selector: 'jhi-crowdfunding-wallet-my-suffix-delete-dialog',
    templateUrl: './crowdfunding-wallet-my-suffix-delete-dialog.component.html'
})
export class CrowdfundingWalletMySuffixDeleteDialogComponent {

    crowdfundingWallet: CrowdfundingWalletMySuffix;

    constructor(
        private crowdfundingWalletService: CrowdfundingWalletMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.crowdfundingWalletService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'crowdfundingWalletListModification',
                content: 'Deleted an crowdfundingWallet'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-crowdfunding-wallet-my-suffix-delete-popup',
    template: ''
})
export class CrowdfundingWalletMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private crowdfundingWalletPopupService: CrowdfundingWalletMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.crowdfundingWalletPopupService
                .open(CrowdfundingWalletMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
