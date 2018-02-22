import { Component, OnInit, OnDestroy } from '@angular/core';
import { ActivatedRoute } from '@angular/router';

import { NgbActiveModal } from '@ng-bootstrap/ng-bootstrap';
import { JhiEventManager } from 'ng-jhipster';

import { InvestmentMySuffix } from './investment-my-suffix.model';
import { InvestmentMySuffixPopupService } from './investment-my-suffix-popup.service';
import { InvestmentMySuffixService } from './investment-my-suffix.service';

@Component({
    selector: 'jhi-investment-my-suffix-delete-dialog',
    templateUrl: './investment-my-suffix-delete-dialog.component.html'
})
export class InvestmentMySuffixDeleteDialogComponent {

    investment: InvestmentMySuffix;

    constructor(
        private investmentService: InvestmentMySuffixService,
        public activeModal: NgbActiveModal,
        private eventManager: JhiEventManager
    ) {
    }

    clear() {
        this.activeModal.dismiss('cancel');
    }

    confirmDelete(id: number) {
        this.investmentService.delete(id).subscribe((response) => {
            this.eventManager.broadcast({
                name: 'investmentListModification',
                content: 'Deleted an investment'
            });
            this.activeModal.dismiss(true);
        });
    }
}

@Component({
    selector: 'jhi-investment-my-suffix-delete-popup',
    template: ''
})
export class InvestmentMySuffixDeletePopupComponent implements OnInit, OnDestroy {

    routeSub: any;

    constructor(
        private route: ActivatedRoute,
        private investmentPopupService: InvestmentMySuffixPopupService
    ) {}

    ngOnInit() {
        this.routeSub = this.route.params.subscribe((params) => {
            this.investmentPopupService
                .open(InvestmentMySuffixDeleteDialogComponent as Component, params['id']);
        });
    }

    ngOnDestroy() {
        this.routeSub.unsubscribe();
    }
}
