/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { HttpResponse } from '@angular/common/http';
import { Observable } from 'rxjs/Observable';

import { NuoTestModule } from '../../../test.module';
import { InvestmentMySuffixDetailComponent } from '../../../../../../main/webapp/app/entities/investment-my-suffix/investment-my-suffix-detail.component';
import { InvestmentMySuffixService } from '../../../../../../main/webapp/app/entities/investment-my-suffix/investment-my-suffix.service';
import { InvestmentMySuffix } from '../../../../../../main/webapp/app/entities/investment-my-suffix/investment-my-suffix.model';

describe('Component Tests', () => {

    describe('InvestmentMySuffix Management Detail Component', () => {
        let comp: InvestmentMySuffixDetailComponent;
        let fixture: ComponentFixture<InvestmentMySuffixDetailComponent>;
        let service: InvestmentMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [InvestmentMySuffixDetailComponent],
                providers: [
                    InvestmentMySuffixService
                ]
            })
            .overrideTemplate(InvestmentMySuffixDetailComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InvestmentMySuffixDetailComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InvestmentMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN

                spyOn(service, 'find').and.returnValue(Observable.of(new HttpResponse({
                    body: new InvestmentMySuffix(123)
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.find).toHaveBeenCalledWith(123);
                expect(comp.investment).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
