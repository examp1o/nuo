/* tslint:disable max-line-length */
import { ComponentFixture, TestBed, async } from '@angular/core/testing';
import { Observable } from 'rxjs/Observable';
import { HttpHeaders, HttpResponse } from '@angular/common/http';

import { NuoTestModule } from '../../../test.module';
import { InvestmentMySuffixComponent } from '../../../../../../main/webapp/app/entities/investment-my-suffix/investment-my-suffix.component';
import { InvestmentMySuffixService } from '../../../../../../main/webapp/app/entities/investment-my-suffix/investment-my-suffix.service';
import { InvestmentMySuffix } from '../../../../../../main/webapp/app/entities/investment-my-suffix/investment-my-suffix.model';

describe('Component Tests', () => {

    describe('InvestmentMySuffix Management Component', () => {
        let comp: InvestmentMySuffixComponent;
        let fixture: ComponentFixture<InvestmentMySuffixComponent>;
        let service: InvestmentMySuffixService;

        beforeEach(async(() => {
            TestBed.configureTestingModule({
                imports: [NuoTestModule],
                declarations: [InvestmentMySuffixComponent],
                providers: [
                    InvestmentMySuffixService
                ]
            })
            .overrideTemplate(InvestmentMySuffixComponent, '')
            .compileComponents();
        }));

        beforeEach(() => {
            fixture = TestBed.createComponent(InvestmentMySuffixComponent);
            comp = fixture.componentInstance;
            service = fixture.debugElement.injector.get(InvestmentMySuffixService);
        });

        describe('OnInit', () => {
            it('Should call load all on init', () => {
                // GIVEN
                const headers = new HttpHeaders().append('link', 'link;link');
                spyOn(service, 'query').and.returnValue(Observable.of(new HttpResponse({
                    body: [new InvestmentMySuffix(123)],
                    headers
                })));

                // WHEN
                comp.ngOnInit();

                // THEN
                expect(service.query).toHaveBeenCalled();
                expect(comp.investments[0]).toEqual(jasmine.objectContaining({id: 123}));
            });
        });
    });

});
