import { Injectable, Component } from '@angular/core';
import { Router } from '@angular/router';
import { NgbModal, NgbModalRef } from '@ng-bootstrap/ng-bootstrap';
import { HttpResponse } from '@angular/common/http';
import { CrowdfundingWalletMySuffix } from './crowdfunding-wallet-my-suffix.model';
import { CrowdfundingWalletMySuffixService } from './crowdfunding-wallet-my-suffix.service';

@Injectable()
export class CrowdfundingWalletMySuffixPopupService {
    private ngbModalRef: NgbModalRef;

    constructor(
        private modalService: NgbModal,
        private router: Router,
        private crowdfundingWalletService: CrowdfundingWalletMySuffixService

    ) {
        this.ngbModalRef = null;
    }

    open(component: Component, id?: number | any): Promise<NgbModalRef> {
        return new Promise<NgbModalRef>((resolve, reject) => {
            const isOpen = this.ngbModalRef !== null;
            if (isOpen) {
                resolve(this.ngbModalRef);
            }

            if (id) {
                this.crowdfundingWalletService.find(id)
                    .subscribe((crowdfundingWalletResponse: HttpResponse<CrowdfundingWalletMySuffix>) => {
                        const crowdfundingWallet: CrowdfundingWalletMySuffix = crowdfundingWalletResponse.body;
                        this.ngbModalRef = this.crowdfundingWalletModalRef(component, crowdfundingWallet);
                        resolve(this.ngbModalRef);
                    });
            } else {
                // setTimeout used as a workaround for getting ExpressionChangedAfterItHasBeenCheckedError
                setTimeout(() => {
                    this.ngbModalRef = this.crowdfundingWalletModalRef(component, new CrowdfundingWalletMySuffix());
                    resolve(this.ngbModalRef);
                }, 0);
            }
        });
    }

    crowdfundingWalletModalRef(component: Component, crowdfundingWallet: CrowdfundingWalletMySuffix): NgbModalRef {
        const modalRef = this.modalService.open(component, { size: 'lg', backdrop: 'static'});
        modalRef.componentInstance.crowdfundingWallet = crowdfundingWallet;
        modalRef.result.then((result) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        }, (reason) => {
            this.router.navigate([{ outlets: { popup: null }}], { replaceUrl: true, queryParamsHandling: 'merge' });
            this.ngbModalRef = null;
        });
        return modalRef;
    }
}
